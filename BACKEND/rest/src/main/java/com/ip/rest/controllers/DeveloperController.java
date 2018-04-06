package com.ip.rest.controllers;

import com.ip.domain.Developer;
import com.ip.domain.DeveloperRequest;
import com.ip.rest.util.DateUtil;
import com.ip.rest.util.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.ip.services.ValidationException.errorMapFromBindingResult;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    @PostMapping()
    public ResponseEntity post(@RequestBody @Valid DeveloperRequest developerRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Model validation failed")
                    .add("errors", errorMapFromBindingResult(bindingResult))
                    .build();

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