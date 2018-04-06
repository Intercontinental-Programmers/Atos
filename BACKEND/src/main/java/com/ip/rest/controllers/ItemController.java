package com.ip.rest.controllers;

import com.ip.domain.Item;
import com.ip.rest.util.DateUtil;
import com.ip.rest.util.ResponseBuilder;
import com.ip.security.util.PermissionValidator;
import com.ip.services.ItemService;
import com.ip.services.ItemServiceImpl;
import com.ip.services.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ip.services.ValidationException.errorMapFromBindingResult;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private ItemService itemService;
    private PermissionValidator validator;

    @Autowired
    public ItemController(ItemServiceImpl itemService, PermissionValidator validator) {
        this.itemService = itemService;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity get(Authentication auth) {

        var result = itemService
                .getAllItems()
                .stream()
                .filter(item -> validator.hasPermission(auth, item))
                .collect(Collectors.toList());

        return ResponseBuilder
                .status(200)
                .body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable long id, Authentication auth) {

        Optional<Item> result = itemService.getItemById(id);

        if (result.isPresent()) {
            if (validator.hasPermission(auth, result.get())) {
                return ResponseBuilder
                        .status(200)
                        .body(result.get());
            }
            return ResponseBuilder
                    .status(403)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 403)
                    .add("message", "You don't have access to this resource")
                    .build();
        }

        return ResponseBuilder
                .status(404)
                .add("timestamp", DateUtil.getTimestamp())
                .add("status", 404)
                .add("message", "Not found")
                .add("errors", Map.of("id", "Item with given id doesn't exist"))
                .build();
    }

    @PostMapping()
    public ResponseEntity post(@Valid @RequestBody Item item, BindingResult bindingResult, Authentication auth) {

        if (bindingResult.hasErrors())
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Model validation failed")
                    .add("errors", errorMapFromBindingResult(bindingResult))
                    .build();

        try {
            item.setOwner(auth.getName());
            itemService.addItem(item);

            return ResponseBuilder
                    .status(201)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 201)
                    .add("message", "Item successfully added")
                    .add("path", "/api/items/" + item.getId())
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

    @PutMapping("{id}")
    public ResponseEntity put(@PathVariable long id, @Valid @RequestBody Item item, BindingResult bindingResult,
                              Authentication auth) {

        if (!validator.hasPermission(auth, item))
            return ResponseBuilder
                    .status(403)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 403)
                    .add("message", "You don't have access to this resource")
                    .build();

        if (bindingResult.hasErrors())
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Model validation failed")
                    .add("errors", errorMapFromBindingResult(bindingResult))
                    .build();

        try {
            itemService.updateItem(id, item);

            return ResponseBuilder
                    .status(200)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 200)
                    .add("message", "Item updated")
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
    public ResponseEntity delete(@PathVariable long id, Authentication auth) throws ValidationException {

        Optional<Item> result = itemService.getItemById(id);

        if (result.isPresent()) {
            if (validator.hasPermission(auth, result.get())) {
                itemService.deleteItem(id);
                return ResponseBuilder
                        .status(204)
                        .build();
            }
            return ResponseBuilder
                    .status(403)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 403)
                    .add("message", "You don't have access to this resource")
                    .build();
        }

        return ResponseBuilder
                .status(404)
                .add("timestamp", DateUtil.getTimestamp())
                .add("status", 404)
                .add("message", "Not found")
                .build();
    }
}