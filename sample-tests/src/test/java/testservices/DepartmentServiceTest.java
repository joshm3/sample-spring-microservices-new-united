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


public class DepartmentServiceTest {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8060/department/";

    @Test
    public void runTests() {
        String id = testAdd();
        testFindById(id);
        testFindAll();
        testFindByOrganization();
        testFindByOrganizationWithEmployees();
    }

    public String testAdd() {
        String url = BASE_URL;

        String requestBody = "{\"organizationId\": 1,\"name\": \"new department\"}";
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
        assertTrue(response.getBody().toString().contains("\"organizationId\":1,\"name\":\"new department\",\"employees\":[]"));
        System.out.println("Response from testFindById: " + response.getBody());
    }

    public void testFindAll() {
        String url = BASE_URL;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Asserting the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().toString().contains("\"organizationId\":1,\"name\":\"new department\",\"employees\":[]"));
        System.out.println("Response from testFindAll: " + response.getBody());
    }

    public void testFindByOrganization() {
        String url = BASE_URL + "organization/1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Asserting the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().toString().contains("\"organizationId\":1,\"name\":\"new department\",\"employees\":[]"));
        System.out.println("Response from testFindByOrganization: " + response.getBody());
    }

    public void testFindByOrganizationWithEmployees() {
        String url = BASE_URL + "organization/1/with-employees";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Asserting the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().toString().contains("\"organizationId\":1,\"name\":\"new department\",\"employees\":[]"));
        System.out.println("Response from testFindByOrganizationWithEmployees: " + response.getBody());
    }
}
