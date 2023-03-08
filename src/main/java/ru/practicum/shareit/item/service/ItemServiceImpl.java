package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.exception.ItemNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserDao;
import ru.practicum.shareit.user.exception.UserNotFoundException;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemStorage;

    private final UserDao userStorage;

    @Override
    public ItemDto addItem(ItemDto itemDto, long userId) {
        userCheckExistence(userId);
        User user = userStorage.getById(userId);
        Item item = ItemMapper.mapToItem(itemDto, user);
        itemStorage.addItem(item);
        itemDto.setId(item.getItemId());
        return itemDto;
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, long itemId, long userId) {
        itemCheckExistence(itemId);
        userCheckExistence(userId);
        itemCheckIntersectionWithUser(itemId, userId);
        Item item = itemStorage.getItemById(itemId);
        item = ItemMapper.mapToItem(itemDto, item);
        itemStorage.updateItem(item);
        itemDto = ItemMapper.mapToDto(item);
        return itemDto;
    }

    @Override
    public void deleteItem(long itemId) {
        itemStorage.deleteItem(itemId);
    }

    @Override
    public ItemDto getItemById(long itemId) {
        itemCheckExistence(itemId);
        Item item = itemStorage.getItemById(itemId);
        return ItemMapper.mapToDto(item);
    }

    @Override
    public List<ItemDto> getAllUserItems(long userId) {
        userCheckExistence(userId);
        List<Item> userItems = itemStorage.getAllUserItems(userId);
        return ItemMapper.mapToDtoList(userItems);
    }

    @Override
    public List<ItemDto> searchItemsByText(String text) {
        List<Item> foundItems = itemStorage.searchItemsByText(text);
        return ItemMapper.mapToDtoList(foundItems);
    }

    private void userCheckExistence(long userId) {
        User user = userStorage.getById(userId);
        if (user == null)
            throw new UserNotFoundException(String.format("Пользователя с ID# %d не существует.", userId));
    }

    private void itemCheckExistence(long itemId) {
        Item item = itemStorage.getItemById(itemId);
        if (item == null)
            throw new ItemNotFoundException(String.format("Предмета с ID# %d не существует.", itemId));
    }

    private void itemCheckIntersectionWithUser(long itemId, long userId) {
        List<Item> items = itemStorage.getAllUserItems(userId);
        items.stream()
                .filter(item -> item.getItemId() == itemId)
                .findAny()
                .orElseThrow(() -> {
                    throw new ItemNotFoundException(
                            String.format("У пользователя с ID# %d нет предмета с ID# %d", userId, itemId)
                    );
                });
    }

}