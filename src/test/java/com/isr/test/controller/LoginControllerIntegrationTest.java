package com.isr.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles(profiles = {"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoginControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getDates_hasBody() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/dates", String.class);
        assertEquals(true, response.hasBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Incorrect Response Status");
    }

    @Test
    public void getUsers_withNoParameters_hasBody() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/users", String.class);
        assertEquals(true, response.hasBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Incorrect Response Status");
    }

    @Test
    public void getUsers_withStartDate_hasBody() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/users?start=20190908", String.class);
        assertEquals(true, response.hasBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Incorrect Response Status");
    }

    @Test
    public void getUsers_withEndDate_hasBody() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/users?end=20180908", String.class);
        assertEquals(true, response.hasBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Incorrect Response Status");
    }

    @Test
    public void getUsers_withStartAndEndDate_hasBody() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/users?start=20180908&end=20190908", String.class);
        assertEquals(true, response.hasBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Incorrect Response Status");
    }

    @Test
    public void getUsers_withInvalidStartAndEndDate() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/users?start=201809s08&end=2019aa0908", String.class);
        assertEquals(true, response.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value(), "Incorrect Response Status");
    }

    @Test
    public void getLogins_hasBody() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/logins?start=20180908&end=20190908" +
                "&attribute1=AA0&attribute2=AA1&attribute3=AA3&attribute4=AA4", String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(true, response.hasBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Incorrect Response Status");
    }

    @Test
    public void getLogins_withMultipleAttribute1_hasBody() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/logins?&attribute1=AA0&attribute1=BB0" +
                "&attribute1=CC0&attribute1=DD0", String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(true, response.hasBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Incorrect Response Status");
    }

    @Test
    public void getLogins_withMultipleAttribute2_hasBody() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/logins?&attribute2=AA1&attribute2=BB1" +
                "&attribute2=CC1&attribute2=DD1", String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(true, response.hasBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Incorrect Response Status");
    }

    @Test
    public void getLogins_withMultipleAttribute3_hasBody() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/logins?&attribute3=AA2&attribute3=BB2" +
                "&attribute3=CC2&attribute3=DD2", String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(true, response.hasBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Incorrect Response Status");
    }

    @Test
    public void getLogins_withMultipleAttribute4_hasBody() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/logins?&attribute4=AA3&attribute4=BB3" +
                "&attribute4=CC3&attribute4=DD3", String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(true, response.hasBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Incorrect Response Status");
    }
}
