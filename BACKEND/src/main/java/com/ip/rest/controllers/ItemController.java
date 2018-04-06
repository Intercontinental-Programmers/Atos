package com.ip.rest.controllers;

import com.ip.domain.Item;
import com.ip.rest.util.DateUtil;
import com.ip.rest.util.ResponseBuilder;
import com.ip.services.ItemService;
import com.ip.services.ItemServiceImpl;
import com.ip.services.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

import static com.ip.services.ValidationException.errorMapFromBindingResult;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity get() {

        return ResponseBuilder
                .status(200)
                .body(itemService.getAllItems());
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable long id) {

        Optional<Item> result = itemService.getItemById(id);

        if (result.isPresent()) {

            return ResponseBuilder
                    .status(200)
                    .body(result.get());
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
    public ResponseEntity post(@Valid @RequestBody Item item, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return ResponseBuilder
                    .status(400)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 400)
                    .add("message", "Model validation failed")
                    .add("errors", errorMapFromBindingResult(bindingResult))
                    .build();

        try {
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
    public ResponseEntity put(@PathVariable long id, @Valid @RequestBody Item item, BindingResult bindingResult) {

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
    public ResponseEntity delete(@PathVariable long id) {

        try {
            itemService.deleteItem(id);
            return ResponseBuilder
                    .status(204)
                    .build();
        } catch (ValidationException e) {
            return ResponseBuilder
                    .status(404)
                    .add("timestamp", DateUtil.getTimestamp())
                    .add("status", 404)
                    .add("message", "Not found")
                    .build();
        }
    }
}