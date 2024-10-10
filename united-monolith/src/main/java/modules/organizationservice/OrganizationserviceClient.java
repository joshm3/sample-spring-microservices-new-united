package modules.organizationservice;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.List;
import modules.departmentservice.DepartmentserviceApi;
import modules.employeeservice.EmployeeserviceApi;
import modules.organizationservice.pl.piomin.services.organization.model.Department;
import modules.organizationservice.pl.piomin.services.organization.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class OrganizationserviceClient {
    @Autowired
    EmployeeserviceApi employeeserviceApi;

    @Autowired
    DepartmentserviceApi departmentserviceApi;

    public ArrayList<Department> getDepartmentOrganizationPathvariableWithemployees(Long organizationId) {
        Long local0;
        if (organizationId != null) {
            local0 = organizationId;
        } else
            local0 = null;

        List<modules.departmentservice.pl.piomin.services.department.model.Department> input = departmentserviceApi.getDepartmentOrganizationPathvariableWithemployees(local0);
        ArrayList<Department> output1 = new ArrayList<Department>();
        for (modules.departmentservice.pl.piomin.services.department.model.Department listType2 : input) {
            Department local3 = null;
            try  {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.registerModule(new com.fasterxml.jackson.datatype.jdk8.Jdk8Module());
                byte[] bytes = mapper.writeValueAsBytes(listType2);
                local3 = mapper.readValue(bytes, new TypeReference<Department>() {});
            } catch (Exception e) {
                System.err.println(e.toString());
            }
            output1.add(local3);
        }
        return output1;
    }

    public ArrayList<Department> getDepartmentOrganizationPathvariable(Long organizationId) {
        Long local0;
        if (organizationId != null) {
            local0 = organizationId;
        } else
            local0 = null;

        List<modules.departmentservice.pl.piomin.services.department.model.Department> input = departmentserviceApi.getDepartmentOrganizationPathvariable(local0);
        ArrayList<Department> output1 = new ArrayList<Department>();
        for (modules.departmentservice.pl.piomin.services.department.model.Department listType2 : input) {
            Department local3 = null;
            try  {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.registerModule(new com.fasterxml.jackson.datatype.jdk8.Jdk8Module());
                byte[] bytes = mapper.writeValueAsBytes(listType2);
                local3 = mapper.readValue(bytes, new TypeReference<Department>() {});
            } catch (Exception e) {
                System.err.println(e.toString());
            }
            output1.add(local3);
        }
        return output1;
    }

    public ArrayList<Employee> getEmployeeOrganizationPathvariable(Long organizationId) {
        Long local0;
        if (organizationId != null) {
            local0 = organizationId;
        } else
            local0 = null;

        List<modules.employeeservice.pl.piomin.services.employee.model.Employee> input = employeeserviceApi.getEmployeeOrganizationPathvariable(local0);
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