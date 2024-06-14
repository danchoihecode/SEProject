package com.chattingweb.backend;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
public class ConversationListTests {
    @Autowired(required = true)
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnConversationList() {
        ResponseEntity<String> response = restTemplate.getForEntity("/conversations/list?user-id=a95e1a0e-1a72-4a3a-8e49-37afb83cc668", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String conversationId1 = documentContext.read("$.[0].conversationId");
        String conversationId2 = documentContext.read("$.[1].conversationId");
        assertThat(conversationId1).isIn("ef11cf55-841d-47b0-b8d7-1e24b0ee2cfc",
                "39dbf560-f6d2-4ada-b9d5-410d9a7b771c");
        assertThat(conversationId2).isIn("ef11cf55-841d-47b0-b8d7-1e24b0ee2cfc",
                "39dbf560-f6d2-4ada-b9d5-410d9a7b771c");
    }
}
