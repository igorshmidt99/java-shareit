package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemDaoImpl implements ItemDao {
    private static final HashMap<Long, Item> items = new HashMap<>(1000);

    @Override
    public Item addItem(Item item) {
        items.putIfAbsent(item.getItemId(), item);
        return item;
    }

    @Override
    public Item updateItem(Item item) {
        long itemId = item.getItemId();

        checkKey(itemId);
        items.put(itemId, item);
        return item;
    }

    @Override
    public void deleteItem(long itemId) {
        checkKey(itemId);
        items.remove(itemId);
    }

    @Override
    public Item getItemById(long itemId) {
        checkKey(itemId);
        return items.get(itemId);
    }

    @Override
    public List<Item> getAllUserItems(long userId) {
        return items.values()
                .stream()
                .filter(item -> item.getOwner() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> searchItemsByText(String text) {
        return items.values().stream()
                .filter(Item::getIsAvailable)
                .filter(item -> item.getName().contains(text))
                .filter(item -> item.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    private void checkKey(long itemId) {
        if (!items.containsKey(itemId)) throw new IllegalArgumentException("Такого объекта не существует.");
    }
}