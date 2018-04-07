package com.ip.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.data.client.HttpClientManager;
import com.ip.data.client.HttpResponse;
import com.ip.data.client.QueryStringSerializer;
import com.ip.domain.Developer;
import com.ip.domain.DeveloperRequest;
import com.ip.rest.util.DateUtil;
import com.ip.rest.util.ResponseBuilder;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/developers")
@CrossOrigin
public class DeveloperController {

    private String dupa;

    @PostMapping("/test")
    public ResponseEntity test(@RequestBody String json) {

       dupa = json;
       return ResponseBuilder.status(200).build();
    }

    @GetMapping("/test")
    public ResponseEntity test2(){
        return ResponseBuilder.status(200).body(dupa);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody String json) {


        try {
            DeveloperRequest request = new ObjectMapper().readValue(json, DeveloperRequest.class);
            HttpClientManager manager = new HttpClientManager();
            String query = QueryStringSerializer.serialize(request);
            HttpResponse response = manager.get("http://localhost:5001/linkedin/api/developers" + query);

            List<Map<String, Object>> res = response
                    .getBody()
                    .toList()
                    .stream()
                    .map(dev -> (Map<String, Object>)dev)
                    .collect(Collectors.toList());

            return ResponseBuilder.status(response.getStatus()).body(res);

        } catch (Exception e) {
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Model validation failed")
                    .build();
        }

    }
}