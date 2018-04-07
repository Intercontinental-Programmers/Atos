package com.ip.controllers;

import com.ip.domain.Developer;
import com.ip.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
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


    private DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public ResponseEntity getDeveloperByLevel(@RequestParam(value = "languages") List<String> mainLanguages,
                                              @RequestParam(value = "cities") List<String> cities,
                                              @RequestParam(value = "levels") List<String> levels,
                                              @RequestParam(value = "student") Boolean isStudent) {
        try {
            List<Developer> result = developerService.query(cities, mainLanguages, levels, isStudent);

            return ResponseEntity.status(200).body(result);

        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

}
