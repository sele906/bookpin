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
}
