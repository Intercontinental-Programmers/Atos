package com.ip.controllers;

import com.ip.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/linkedin/api/developers")
public class DeveloperController {

    private DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService){
        this.developerService=developerService;
    }

    @GetMapping
    public ResponseEntity getDeveloperByLevel (@RequestParam(value = "mainLanguage") List<String> mainLanguages,
                                               @RequestParam(value = "city") List<String> cities,
                                               @RequestParam(value = "level") List<String> levels,
                                               @RequestParam(value = "isStatus")  Boolean isStudent){
        try{
            return null;
        }catch (Exception e){
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

}
