package com.ip.rest.controllers;

import com.ip.domain.AppUser;
import com.ip.rest.util.DateUtil;
import com.ip.rest.util.ResponseBuilder;
import com.ip.rest.util.UserResponse;
import com.ip.services.AppUserService;
import com.ip.services.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ip.services.ValidationException.errorMapFromBindingResult;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private AppUserService appUserService;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserController(AppUserService appUserService, BCryptPasswordEncoder encoder) {
        this.appUserService = appUserService;
        this.encoder = encoder;
    }

    @GetMapping()
    public ResponseEntity get() {

        return ResponseBuilder
                .status(200)
                .body(appUserService
                        .getAllUsers()
                        .stream()
                        .map(UserResponse::new)
                        .collect(Collectors.toSet()));
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable long id) {

        Optional<AppUser> result = appUserService.getUser(id);

        if (!result.isPresent()) {
            return ResponseBuilder
                    .status(404)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 404)
                    .add("message", "Not found")
                    .add("errors", Map.of("id", "User with given id doesn't exist"))
                    .build();
        }

        return ResponseBuilder
                .status(200)
                .body(new UserResponse(result.get()));
    }


    @PutMapping("{id}")
    public ResponseEntity put(@PathVariable long id, @Valid @RequestBody AppUser user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Model validation failed")
                    .add("errors", errorMapFromBindingResult(bindingResult))
                    .build();
        }

        try {
            user.encodePassword(encoder);
            appUserService.updateUser(id, user);

            return ResponseBuilder
                    .status(200)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 200)
                    .add("message", "User updated")
                    .build();

        } catch (ValidationException e) {
            return ResponseBuilder
                    .status(404)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 404)
                    .add("message", "Not found")
                    .add("errors", e.getErrors())
                    .build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {

        try {
            appUserService.deleteUser(id);
            return ResponseBuilder
                    .status(204)
                    .build();

        } catch (ValidationException e) {
            return ResponseBuilder
                    .status(404)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 404)
                    .add("message", "Not found")
                    .add("errors", e.getErrors())
                    .build();
        }
    }
}