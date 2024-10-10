package modules.departmentservice.pl.piomin.services.department.controller;
import java.util.List;
import modules.departmentservice.DepartmentserviceClient;
import modules.departmentservice.pl.piomin.services.department.model.Department;
import modules.departmentservice.pl.piomin.services.department.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    @Autowired
    DepartmentserviceClient departmentserviceClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    DepartmentRepository repository;

    public DepartmentController(DepartmentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/")
    public Department add(@RequestBody
    Department department) {
        LOGGER.info("Department add: {}", department);
        return repository.add(department);
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable("id")
    Long id) {
        LOGGER.info("Department find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/")
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return repository.findAll();
    }

    @GetMapping("/organization/{organizationId}")
    public List<Department> findByOrganization(@PathVariable("organizationId")
    Long organizationId) {
        LOGGER.info("Department find: organizationId={}", organizationId);
        return repository.findByOrganization(organizationId);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId")
    Long organizationId) {
        LOGGER.info("Department find with employees: organizationId={}", organizationId);
        List<Department> departments = repository.findByOrganization(organizationId);
        departments.forEach(d -> d.setEmployees(departmentserviceClient.getEmployeeDepartmentPathvariable(d.getId())));
        return departments;
    }
}