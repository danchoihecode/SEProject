package com.chattingweb.backend;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnCorrectAdmin(){
//        ResponseEntity<String> response = restTemplate.getForEntity("/admin/0", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        Number id = documentContext.read("$.id");
//        assertThat(id).isEqualTo(0);
//        String email = documentContext.read("$.adminEmail");
//        assertThat(email).isEqualTo("admin@gmail.com");
    }
}
