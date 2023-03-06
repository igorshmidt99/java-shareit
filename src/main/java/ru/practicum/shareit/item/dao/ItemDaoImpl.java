package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.exception.ItemNotFoundException;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemDaoImpl implements ItemDao {
    private static final HashMap<Long, Item> items = new HashMap<>(10000);

    @Override
    public Item addItem(Item item) {
        items.putIfAbsent(item.getItemId(), item);
        return item;
    }

    @Override
    public Item updateItem(Item item) {
        long itemId = item.getItemId();

        checkAvailability(itemId);
        items.put(itemId, item);
        return item;
    }

    @Override
    public void deleteItem(long itemId) {
        checkAvailability(itemId);
        items.remove(itemId);
    }

    @Override
    public Item getItemById(long itemId) {
        checkAvailability(itemId);
        return items.get(itemId);
    }

    @Override
    public List<Item> getAllUserItems(long userId) {
        return items.values().stream()
                .filter(item -> item.getOwner() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> searchItemsByText(String text) {
        return items.values().stream()
                .filter(Item::getIsAvailable)
                .filter(item -> matchRegions(item, text.toLowerCase()))
                .collect(Collectors.toList());
    }

    private boolean matchRegions(Item item, String text) {
        if (text.isBlank()) return false;
        String description = item.getDescription().toLowerCase();
        String name = item.getName().toLowerCase();
        int startNameIndex = name.indexOf(text);
        int startDescriptionIndex = description.indexOf(text);
        int textLength = text.length();

        return description.regionMatches(true, startDescriptionIndex, text, 0, textLength)
                || name.regionMatches(true, startNameIndex, text, 0, textLength);
    }

    private void checkAvailability(long itemId) {
        if (!items.containsKey(itemId))
            throw new ItemNotFoundException(String.format("Oбъекта c ID# %d не существует.", itemId));
    }
}