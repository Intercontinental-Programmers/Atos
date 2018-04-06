package com.ip.services;

import org.springframework.validation.BindingResult;

import java.util.LinkedHashMap;
import java.util.Map;

public class ValidationException extends Exception {

    private Map<String, String> errors;

    public ValidationException(String field, String error) {
        this.errors = new LinkedHashMap<>();
        this.errors.put(field, error);
    }

    public ValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }


    public static Map<String, String> errorMapFromBindingResult(BindingResult bindingResult) {
        var errorObject = new LinkedHashMap<String, String>();

        bindingResult.getFieldErrors().forEach(error -> {
            if (!errorObject.containsKey(error.getField())) {
                errorObject.put(error.getField(), error.getDefaultMessage());
            }
        });
        return errorObject;
    }
}
