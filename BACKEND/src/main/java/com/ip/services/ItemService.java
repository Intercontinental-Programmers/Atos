package com.ip.services;

import com.ip.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllItems();

    Optional<Item> getItemById(long id);

    void addItem(Item item) throws ValidationException;

    void updateItem(long id, Item item) throws ValidationException;

    void deleteItem(long id) throws ValidationException;
}
