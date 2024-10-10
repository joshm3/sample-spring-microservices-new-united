package modules.departmentservice;
import java.util.List;
import modules.departmentservice.pl.piomin.services.department.controller.DepartmentController;
import modules.departmentservice.pl.piomin.services.department.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class DepartmentserviceApi {
    @Autowired
    DepartmentController departmentController;

    public List<Department> getDepartmentOrganizationPathvariableWithemployees(Long organizationId) {
        return departmentController.findByOrganizationWithEmployees(organizationId);
    }

    public List<Department> getDepartmentOrganizationPathvariable(Long organizationId) {
        return departmentController.findByOrganization(organizationId);
    }
}