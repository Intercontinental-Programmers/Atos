package com.ip.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.data.client.HttpClientManager;
import com.ip.data.client.QueryStringSerializer;
import com.ip.domain.AppUser;
import com.ip.domain.Developer;
import com.ip.domain.DeveloperRequest;
import com.ip.rest.util.DateUtil;
import com.ip.rest.util.ResponseBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static com.ip.services.ValidationException.errorMapFromBindingResult;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    @PostMapping()
    public ResponseEntity post(@RequestBody String json) {


        try {
            DeveloperRequest request = new ObjectMapper().readValue(json, DeveloperRequest.class);
            HttpClientManager manager = new HttpClientManager();
            manager.get("http://localhost:8090/linkedin/api/developers" + QueryStringSerializer.serialize(request));

        } catch (Exception e) {
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Model validation failed")
                    .build();
        }

        List<Developer> result = new ArrayList<>();
        List<String> languages = new ArrayList<>();
        languages.add("Python");
        languages.add("Java");
        result.add(new Developer("Adrian", "Maślak", "email@o2.pl",
                "Junior Frontend developer", "www.mojastrona.pl", languages, "Wrocław", true));
        result.add(new Developer("Marian", "Cieślak", "emaaail@o2.pl",
                "Senior Javascript developer", "www.mojaaaastrona.pl", languages, "Poznań", false));
        result.add(new Developer("Dorian", "Maślak", "mójemail@o2.pl",
                "Java developer", "www.mojastronazdanymi.pl", languages, "Warszawa", false));

        return ResponseBuilder.status(200).body(result);
    }

    @GetMapping
    public ResponseEntity get() {

        List<Developer> result = new ArrayList<>();
        List<String> languages = new ArrayList<>();
        languages.add("Python");
        languages.add("Java");
        result.add(new Developer("Adrian", "Maślak", "email@o2.pl",
                "Junior Frontend developer", "www.mojastrona.pl", languages, "Wrocław", true));
        result.add(new Developer("Marian", "Cieślak", "emaaail@o2.pl",
                "Senior Javascript developer", "www.mojaaaastrona.pl", languages, "Poznań", false));
        result.add(new Developer("Dorian", "Maślak", "mójemail@o2.pl",
                "Java developer", "www.mojastronazdanymi.pl", languages, "Warszawa", false));

        return ResponseBuilder.status(200).body(result);
    }
}