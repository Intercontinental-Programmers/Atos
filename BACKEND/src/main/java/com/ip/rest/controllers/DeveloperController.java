package com.ip.rest.controllers;

import com.ip.domain.Developer;
import com.ip.domain.DeveloperRequest;
import com.ip.rest.util.DateUtil;
import com.ip.rest.util.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.ip.services.ValidationException.errorMapFromBindingResult;

@RestController
@RequestMapping("/api/items")
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
        result.add(new Developer("Adrian", "Maślak", "email@o2.pl", "Frontend developer", "www.mojastrona.pl", languages));
        result.add(new Developer("Marian", "Cieślak", "emaaail@o2.pl", "Backend developer", "www.mojaaaastrona.pl", languages));
        result.add(new Developer("Dorian", "Maślak", "mójemail@o2.pl", "Java developer", "www.mojastronazdanymi.pl", languages));

        return ResponseBuilder.status(200).body(result);
    }

}