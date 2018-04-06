package com.ip.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name has to be between 2 and 50 characters long")
    private String name;

    @NotNull(message = "Description cannot be null")
    @Size(min = 5, max = 256, message = "Description has to be between 5 and 256 characters long")
    private String description;

    public Item() {
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void update(Item item) {
        this.name = item.name;
        this.description = item.description;
    }
}
