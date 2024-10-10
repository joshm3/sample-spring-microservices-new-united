package modules.departmentservice;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.List;
import modules.departmentservice.pl.piomin.services.department.model.Employee;
import modules.employeeservice.EmployeeserviceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class DepartmentserviceClient {
    @Autowired
    EmployeeserviceApi employeeserviceApi;

    public ArrayList<Employee> getEmployeeDepartmentPathvariable(Long departmentId) {
        Long local0;
        if (departmentId != null) {
            local0 = departmentId;
        } else
            local0 = null;

        List<modules.employeeservice.pl.piomin.services.employee.model.Employee> input = employeeserviceApi.getEmployeeDepartmentPathvariable(local0);
        ArrayList<Employee> output1 = new ArrayList<Employee>();
        for (modules.employeeservice.pl.piomin.services.employee.model.Employee listType2 : input) {
            Employee local3 = null;
            try  {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.registerModule(new com.fasterxml.jackson.datatype.jdk8.Jdk8Module());
                byte[] bytes = mapper.writeValueAsBytes(listType2);
                local3 = mapper.readValue(bytes, new TypeReference<Employee>() {});
            } catch (Exception e) {
                System.err.println(e.toString());
            }
            output1.add(local3);
        }
        return output1;
    }
}