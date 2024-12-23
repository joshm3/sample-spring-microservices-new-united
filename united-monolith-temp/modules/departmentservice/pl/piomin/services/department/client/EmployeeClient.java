package modules.departmentservice.pl.piomin.services.department.client;
import java.util.List;
import modules.departmentservice.pl.piomin.services.department.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "employee-service")
public interface EmployeeClient {
    @GetMapping("/department/{departmentId}")
    List<Employee> findByDepartment(@PathVariable("departmentId")
    Long departmentId);
}