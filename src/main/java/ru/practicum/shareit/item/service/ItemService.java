package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {

    ItemDto addItem(ItemDto itemDto, long userId);

    ItemDto updateItem(ItemDto itemDto, long itemId, long userId);

    void deleteItem(long itemId);

    ItemDto getItemById(long itemId);

    List<ItemDto> getAllUserItems(long userId);

    List<ItemDto> searchItemsByText(String text);
}