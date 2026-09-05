package com.sele906.api.library.controller;

import com.sele906.api.library.service.LibraryService;
import com.sele906.api.library.domain.Library;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.HtmlUtils;
import tools.jackson.databind.JsonNode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/test")
    public Library test() {
        return libraryService.findOne();
    }

    @PostMapping("/syncLibraries")
    public int syncLibraries() {
        return libraryService.syncLibraries();
    }


}
