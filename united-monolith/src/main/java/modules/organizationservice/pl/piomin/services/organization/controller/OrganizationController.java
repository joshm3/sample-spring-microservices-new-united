package modules.organizationservice.pl.piomin.services.organization.controller;
import java.util.List;
import modules.organizationservice.OrganizationserviceClient;
import modules.organizationservice.pl.piomin.services.organization.client.DepartmentClient;
import modules.organizationservice.pl.piomin.services.organization.client.EmployeeClient;
import modules.organizationservice.pl.piomin.services.organization.model.Organization;
import modules.organizationservice.pl.piomin.services.organization.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = "/organization")
public class OrganizationController {
    @Autowired
    OrganizationserviceClient organizationserviceClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    OrganizationRepository repository;

    @Autowired
    DepartmentClient departmentClient;

    @Autowired
    EmployeeClient employeeClient;

    @PostMapping("/")
    public Organization add(@RequestBody
    Organization organization) {
        LOGGER.info("Organization add: {}", organization);
        return repository.add(organization);
    }

    @GetMapping("/")
    public List<Organization> findAll() {
        LOGGER.info("Organization find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Organization findById(@PathVariable("id")
    Long id) {
        LOGGER.info("Organization find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/{id}/with-departments")
    public Organization findByIdWithDepartments(@PathVariable("id")
    Long id) {
        LOGGER.info("Organization find: id={}", id);
        Organization organization = repository.findById(id);
        organization.setDepartments(organizationserviceClient.getDepartmentOrganizationPathvariable(organization.getId()));
        return organization;
    }

    @GetMapping("/{id}/with-departments-and-employees")
    public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id")
    Long id) {
        LOGGER.info("Organization find: id={}", id);
        Organization organization = repository.findById(id);
        organization.setDepartments(organizationserviceClient.getDepartmentOrganizationPathvariableWithemployees(organization.getId()));
        return organization;
    }

    @GetMapping("/{id}/with-employees")
    public Organization findByIdWithEmployees(@PathVariable("id")
    Long id) {
        LOGGER.info("Organization find: id={}", id);
        Organization organization = repository.findById(id);
        organization.setEmployees(organizationserviceClient.getEmployeeOrganizationPathvariable(organization.getId()));
        return organization;
    }
}