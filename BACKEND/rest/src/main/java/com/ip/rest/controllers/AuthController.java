package com.ip.rest.controllers;

import com.ip.domain.AppUser;
import com.ip.rest.util.DateUtil;
import com.ip.rest.util.ResponseBuilder;
import com.ip.services.AppUserService;
import com.ip.services.ValidationException;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ip.services.ValidationException.errorMapFromBindingResult;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AppUserService appUserService;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public AuthController(AppUserService appUserService, BCryptPasswordEncoder encoder) {

        this.appUserService = appUserService;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody AppUser user, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Model validation failed")
                    .add("errors", errorMapFromBindingResult(bindingResult))
                    .build();


        try {
            user.encodePassword(encoder);
            appUserService.addUser(user);

            return ResponseBuilder
                    .status(201)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 201)
                    .add("message", "User successfully registered")
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
    public ResponseEntity registerAdmin(@Valid @RequestBody AppUser user, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Model validation failed")
                    .add("errors", errorMapFromBindingResult(bindingResult))
                    .build();

        try {
            user.encodePassword(encoder);
            user.setAdminAccount();
            appUserService.addUser(user);

            return ResponseBuilder
                    .status(201)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 201)
                    .add("message", "User successfully registered")
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