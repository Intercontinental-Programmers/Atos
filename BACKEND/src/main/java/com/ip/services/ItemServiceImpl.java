package com.ip.services;

import com.ip.data.ItemRepository;
import com.ip.domain.Item;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private ItemRepository repository;

    @Autowired
    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getAllItems() {

        return Lists.newArrayList(repository.findAll());
    }

    public Optional<Item> getItemById(long id) {
        return repository.findById(id);
    }

    public void addItem(Item item) throws ValidationException {

        if (repository.existsByName(item.getName()))
            throw new ValidationException("name", "Item with given name already exists");

        repository.save(item);
    }

    @Override
    public void updateItem(long id, Item item) throws ValidationException {


        Item result = repository
                .findById(id)
                .orElseThrow(() -> new ValidationException("id", "Item with given id doesn't exist"));

        if (repository.existsByName(item.getName()))
            throw new ValidationException("name", "Item with given name already exists");

        result.update(item);
    }

    public void deleteItem(long id) throws ValidationException {

        if (!repository.existsById(id))
            throw new ValidationException("id", "Item with given id doesn't exist");

        repository.deleteById(id);
    }
}
