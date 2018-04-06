package com.ip.rest.util;

import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;


public class ResponseBuilder {

    private int statusCode;
    private Map<String, Object> body = new LinkedHashMap<>();

    private ResponseBuilder(int statusCode) {

        this.statusCode = statusCode;
    }

    public static ResponseBuilder status(int statusCode) {
        return new ResponseBuilder(statusCode);
    }

    public ResponseBuilder add(String key, Object value) {

        body.put(key, value);
        return this;
    }

    public ResponseEntity body(Object body) {
        return ResponseEntity.status(statusCode).body(body);
    }

    public ResponseEntity build() {

        if (body.isEmpty())
            return ResponseEntity.status(statusCode).build();

        return ResponseEntity.status(statusCode).body(body);
    }
}

