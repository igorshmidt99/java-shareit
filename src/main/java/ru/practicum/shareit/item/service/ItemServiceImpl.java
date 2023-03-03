package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemDao storage;
    private final ItemMapper mapper;

    @Override
    public ItemDto addItem(ItemDto itemDto, long userId) {
        Item item = mapper.mapToItem(itemDto, userId);
        storage.addItem(item);
        itemDto.setId(item.getItemId());
        return itemDto;
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, long itemId, long userId) {
        Item item = mapper.mapToItem(itemDto, userId, itemId);
        storage.updateItem(item);
        itemDto.setId(itemId);
        return itemDto;
    }

    @Override
    public void deleteItem(long itemId) {
        storage.deleteItem(itemId);
    }

    @Override
    public ItemDto getItemById(long itemId) {
        Item item = storage.getItemById(itemId);
        return mapper.mapToDto(item);
    }

    @Override
    public List<ItemDto> getAllUserItems(long userId) {
        List<Item> userItems = storage.getAllUserItems(userId);
        return mapper.mapToDtoList(userItems);
    }

    @Override
    public List<ItemDto> searchItemsByText(String text) {
        List<Item> foundItems = storage.searchItemsByText(text);
        return mapper.mapToDtoList(foundItems);
    }

}