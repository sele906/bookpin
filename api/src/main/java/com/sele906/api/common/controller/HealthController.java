package com.sele906.api.common.controller;

import com.sele906.api.common.repository.ConnectionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class HealthController {

    @Value("${LIB_API_KEY}")
    String authKey;

    @Autowired
    private ConnectionRepository connectionRepository;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> getHealth() {
        return ResponseEntity.ok(
                Map.of("status", "ok")
        );
    }

    @GetMapping("/db-health")
    public Map<String, Object> dbHealth() {
        Integer result = connectionRepository.connectionTest();

        return Map.of(
                "database", "connected",
                "result", result
        );
    }

    @GetMapping("/connection")
    public ResponseEntity<JsonNode> getConnection() {

        RestClient restClient = RestClient.builder()
                .baseUrl("https://data4library.kr")
                .build();

        JsonNode response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/libSrch")
                        .queryParam("authKey", authKey)
                        .queryParam("pageNo", 1)
                        .queryParam("pageSize", 2)
                        .queryParam("format", "json")
                        .build())
                .retrieve()
                .body(JsonNode.class);

        return ResponseEntity.ok(response);
    }

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @PostConstruct
    public void checkEnv() {
        System.out.println("DB username = " + username);
        System.out.println("ENV password length = "
                + System.getenv("DB_PASSWORD").length());
    }
}
