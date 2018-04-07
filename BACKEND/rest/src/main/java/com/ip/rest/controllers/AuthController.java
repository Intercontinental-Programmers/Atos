package com.ip.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.domain.AppUser;
import com.ip.rest.util.DateUtil;
import com.ip.rest.util.ResponseBuilder;
import com.ip.services.AppUserService;
import com.ip.services.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private AppUserService appUserService;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public AuthController(AppUserService appUserService, BCryptPasswordEncoder encoder) {

        this.appUserService = appUserService;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody String json) {
        try {
            AppUser user = new ObjectMapper().readValue(json, AppUser.class);
            user.encodePassword(encoder);
            appUserService.addUser(user);

            return ResponseBuilder
                    .status(201)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 201)
                    .add("message", "User successfully registered")
                    .build();
        } catch (IOException e) {
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Model validation failed")
                    .build();
        } catch (ValidationException e) {
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Already exists")
                    .add("errors", e.getErrors())
                    .build();
        }
    }

    @PostMapping("/admin/register")
    public ResponseEntity registerAdmin(@RequestBody String json) {

        try {
            AppUser user = new ObjectMapper().readValue(json, AppUser.class);
            user.encodePassword(encoder);
            user.setAdminAccount();
            appUserService.addUser(user);

            return ResponseBuilder
                    .status(201)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 201)
                    .add("message", "User successfully registered")
                    .build();

        } catch (IOException e) {
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Model validation failed")
                    .build();
        } catch (ValidationException e) {
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Already exists")
                    .add("errors", e.getErrors())
                    .build();
        }
    }
}