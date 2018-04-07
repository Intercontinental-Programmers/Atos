package com.ip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/linkedin/api/developers")
public class DeveloperController {


    @GetMapping
    public ResponseEntity getDeveloperByLevel(@RequestParam(value = "languages") List<String> mainLanguages,
                                              @RequestParam(value = "cities") List<String> cities,
                                              @RequestParam(value = "levels") List<String> levels,
                                              @RequestParam(value = "student") Boolean isStudent) {
        try {
            return null;
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

}
