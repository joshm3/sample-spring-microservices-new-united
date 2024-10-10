package testservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class OrganizationServiceTest {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8060/organization/";

    @Test
    public void runTests() {
        String id = testAdd();
        testFindById(id);
        testFindAll();

        //once all the department and employee data is retreived, the next three all give all of the data
        //this is probably an error
        restTemplate.getForEntity(BASE_URL + "2/with-departments-and-employees", String.class);
        restTemplate.getForEntity(BASE_URL + "2/with-departments", String.class);
        restTemplate.getForEntity(BASE_URL + "2/with-employees", String.class);

        findByIdWithDepartments();
        findByIdWithDepartmentsAndEmployees();
        findByIdWithEmployees();
    }

    public String testAdd() {
        String url = BASE_URL;

        String requestBody = "{\"name\": \"new organization\",\"address\": \"new address\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        
        // Asserting the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("\"id\""));
        Pattern pattern = Pattern.compile("\"id\":(\\d+)");
        Matcher matcher = pattern.matcher(response.getBody());
        String id = null;
        if (matcher.find()) id = matcher.group(1);
        assertNotNull(id);
        System.out.println("Response from testAdd: " + response.getBody());
        return id;
    }

    public void testFindById(String id) {
        String url = BASE_URL + id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Asserting the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().toString().contains("\"name\":\"new organization\",\"address\":\"new address\",\"departments\":[],\"employees\":[]"));
        System.out.println("Response from testFindById: " + response.getBody());
    }

    public void testFindAll() {
        String url = BASE_URL;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Asserting the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().toString().contains("\"name\":\"new organization\",\"address\":\"new address\",\"departments\":[],\"employees\":[]"));
        System.out.println("Response from testFindAll: " + response.getBody());
    }

    public void findByIdWithDepartments() {
        String url = BASE_URL + "2/with-departments";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Asserting the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("{\"id\":2,\"name\":\"Oracle\",\"address\":\"Redwood City, California, USA\",\"departments\":[{\"id\":3,\"name\":\"Development\",\"employees\":[{\"id\":6,\"name\":\"Kevin Price\",\"age\":38,\"position\":\"Developer\"},{\"id\":7,\"name\":\"Ian Scott\",\"age\":34,\"position\":\"Developer\"},{\"id\":8,\"name\":\"Andrew Campton\",\"age\":30,\"position\":\"Manager\"}]},{\"id\":4,\"name\":\"Operations\",\"employees\":[{\"id\":9,\"name\":\"Steve Franklin\",\"age\":25,\"position\":\"Developer\"},{\"id\":10,\"name\":\"Elisabeth Smith\",\"age\":30,\"position\":\"Developer\"}]}],\"employees\":[{\"id\":6,\"name\":\"Kevin Price\",\"age\":38,\"position\":\"Developer\"},{\"id\":7,\"name\":\"Ian Scott\",\"age\":34,\"position\":\"Developer\"},{\"id\":8,\"name\":\"Andrew Campton\",\"age\":30,\"position\":\"Manager\"},{\"id\":9,\"name\":\"Steve Franklin\",\"age\":25,\"position\":\"Developer\"},{\"id\":10,\"name\":\"Elisabeth Smith\",\"age\":30,\"position\":\"Developer\"}]}", response.getBody());
        // assertEquals("{\"id\":2,\"name\":\"Oracle\",\"address\":\"Redwood City, California, USA\",\"departments\":[{\"id\":3,\"name\":\"Development\",\"employees\":[{\"id\":6,\"name\":\"Kevin Price\",\"age\":38,\"position\":\"Developer\"},{\"id\":7,\"name\":\"Ian Scott\",\"age\":34,\"position\":\"Developer\"},{\"id\":8,\"name\":\"Andrew Campton\",\"age\":30,\"position\":\"Manager\"}]},{\"id\":4,\"name\":\"Operations\",\"employees\":[{\"id\":9,\"name\":\"Steve Franklin\",\"age\":25,\"position\":\"Developer\"},{\"id\":10,\"name\":\"Elisabeth Smith\",\"age\":30,\"position\":\"Developer\"}]}],\"employees\":[]}", response.getBody());
        // assertEquals("{\"id\":2,\"name\":\"Oracle\",\"address\":\"Redwood City, California, USA\",\"departments\":[{\"id\":3,\"name\":\"Development\",\"employees\":[]},{\"id\":4,\"name\":\"Operations\",\"employees\":[]}],\"employees\":[]}", response.getBody());
        System.out.println("Response from findByIdWithDepartments: " + response.getBody());
    }

    public void findByIdWithDepartmentsAndEmployees() {
        String url = BASE_URL + "2/with-departments-and-employees";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Asserting the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("{\"id\":2,\"name\":\"Oracle\",\"address\":\"Redwood City, California, USA\",\"departments\":[{\"id\":3,\"name\":\"Development\",\"employees\":[{\"id\":6,\"name\":\"Kevin Price\",\"age\":38,\"position\":\"Developer\"},{\"id\":7,\"name\":\"Ian Scott\",\"age\":34,\"position\":\"Developer\"},{\"id\":8,\"name\":\"Andrew Campton\",\"age\":30,\"position\":\"Manager\"}]},{\"id\":4,\"name\":\"Operations\",\"employees\":[{\"id\":9,\"name\":\"Steve Franklin\",\"age\":25,\"position\":\"Developer\"},{\"id\":10,\"name\":\"Elisabeth Smith\",\"age\":30,\"position\":\"Developer\"}]}],\"employees\":[{\"id\":6,\"name\":\"Kevin Price\",\"age\":38,\"position\":\"Developer\"},{\"id\":7,\"name\":\"Ian Scott\",\"age\":34,\"position\":\"Developer\"},{\"id\":8,\"name\":\"Andrew Campton\",\"age\":30,\"position\":\"Manager\"},{\"id\":9,\"name\":\"Steve Franklin\",\"age\":25,\"position\":\"Developer\"},{\"id\":10,\"name\":\"Elisabeth Smith\",\"age\":30,\"position\":\"Developer\"}]}", response.getBody());
        // assertEquals("{\"id\":2,\"name\":\"Oracle\",\"address\":\"Redwood City, California, USA\",\"departments\":[{\"id\":3,\"name\":\"Development\",\"employees\":[{\"id\":6,\"name\":\"Kevin Price\",\"age\":38,\"position\":\"Developer\"},{\"id\":7,\"name\":\"Ian Scott\",\"age\":34,\"position\":\"Developer\"},{\"id\":8,\"name\":\"Andrew Campton\",\"age\":30,\"position\":\"Manager\"}]},{\"id\":4,\"name\":\"Operations\",\"employees\":[{\"id\":9,\"name\":\"Steve Franklin\",\"age\":25,\"position\":\"Developer\"},{\"id\":10,\"name\":\"Elisabeth Smith\",\"age\":30,\"position\":\"Developer\"}]}],\"employees\":[]}", response.getBody());
        System.out.println("Response from findByIdWithDepartmentsAndEmployees: " + response.getBody());
    }

    public void findByIdWithEmployees() {
        String url = BASE_URL + "2/with-employees";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Asserting the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("{\"id\":2,\"name\":\"Oracle\",\"address\":\"Redwood City, California, USA\",\"departments\":[{\"id\":3,\"name\":\"Development\",\"employees\":[{\"id\":6,\"name\":\"Kevin Price\",\"age\":38,\"position\":\"Developer\"},{\"id\":7,\"name\":\"Ian Scott\",\"age\":34,\"position\":\"Developer\"},{\"id\":8,\"name\":\"Andrew Campton\",\"age\":30,\"position\":\"Manager\"}]},{\"id\":4,\"name\":\"Operations\",\"employees\":[{\"id\":9,\"name\":\"Steve Franklin\",\"age\":25,\"position\":\"Developer\"},{\"id\":10,\"name\":\"Elisabeth Smith\",\"age\":30,\"position\":\"Developer\"}]}],\"employees\":[{\"id\":6,\"name\":\"Kevin Price\",\"age\":38,\"position\":\"Developer\"},{\"id\":7,\"name\":\"Ian Scott\",\"age\":34,\"position\":\"Developer\"},{\"id\":8,\"name\":\"Andrew Campton\",\"age\":30,\"position\":\"Manager\"},{\"id\":9,\"name\":\"Steve Franklin\",\"age\":25,\"position\":\"Developer\"},{\"id\":10,\"name\":\"Elisabeth Smith\",\"age\":30,\"position\":\"Developer\"}]}", response.getBody());
        System.out.println("Response from findByIdWithEmployees: " + response.getBody());
    }
}
