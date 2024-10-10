package modules.employeeservice;
import java.util.List;
import modules.employeeservice.pl.piomin.services.employee.controller.EmployeeController;
import modules.employeeservice.pl.piomin.services.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class EmployeeserviceApi {
    @Autowired
    EmployeeController employeeController;

    public List<Employee> getEmployeeDepartmentPathvariable(Long departmentId) {
        return employeeController.findByDepartment(departmentId);
    }

    public List<Employee> getEmployeeOrganizationPathvariable(Long organizationId) {
        return employeeController.findByOrganization(organizationId);
    }
}