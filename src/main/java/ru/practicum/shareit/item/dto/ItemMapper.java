package ru.practicum.shareit.item.dto;

import lombok.Getter;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class ItemMapper {

    private static long ITEM_ID;

    private ItemMapper() { }

    public static Item mapToItem(ItemDto itemDto, User user) {
        Item item = Item.builder()
                .description(itemDto.getDescription())
                .isAvailable(itemDto.getAvailable())
                .name(itemDto.getName())
                .owner(user)
                .build();
        item.setItemId(createId(item.getItemId()));
        return item;
    }

    public static Item mapToItem(ItemDto itemDto, Item item) {
        String newDescription = itemDto.getDescription();
        Boolean newAvailable = itemDto.getAvailable();
        String newName = itemDto.getName();
        if (newName != null) item.setName(newName);
        if (newAvailable != null) item.setIsAvailable(newAvailable);
        if (newDescription != null) item.setDescription(newDescription);
        return item;
    }

    public static ItemDto mapToDto(Item item) {
        return ItemDto.builder()
                .id(item.getItemId())
                .description(item.getDescription())
                .available(item.getIsAvailable())
                .name(item.getName())
                .build();
    }

    public static List<ItemDto> mapToDtoList(List<Item> items) {
        return items.stream()
                .map(ItemMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private static long createId(Long itemId) {
        if (itemId == null || itemId <= 0) return ++ITEM_ID;
        if (itemId > ITEM_ID) ITEM_ID = itemId;
        return itemId;
    }
}