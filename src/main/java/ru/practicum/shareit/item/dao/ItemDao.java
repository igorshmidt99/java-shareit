package ru.practicum.shareit.item.dao;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemDao {

    Item addItem(Item item);

    Item updateItem(Item item);

    void deleteItem(long itemId);

    Item getItemById(long itemId);

    List<Item> getAllUserItems(long userId);

    List<Item> searchItemsByText(String text);

}