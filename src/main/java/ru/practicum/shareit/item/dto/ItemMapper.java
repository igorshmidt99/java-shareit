package ru.practicum.shareit.item.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
public class ItemMapper {
    private static long ITEM_ID;

    public Item mapToItem(ItemDto itemDto, long userId) {
        Item item = Item.builder()
                .description(itemDto.getDescription())
                .isAvailable(itemDto.getAvailable())
                .name(itemDto.getName())
                .owner(userId)
                .build();
        item.setItemId(createId(item.getItemId()));
        return item;
    }

    public Item mapToItem(ItemDto itemDto, Item item) {
        String newDescription = itemDto.getDescription();
        Boolean newAvailable = itemDto.getAvailable();
        String newName = itemDto.getName();
        if (newName != null) item.setName(newName);
        if (newAvailable != null) item.setIsAvailable(newAvailable);
        if (newDescription != null) item.setDescription(newDescription);
        return item;
    }

    public ItemDto mapToDto(Item item) {
        return ItemDto.builder()
                .id(item.getItemId())
                .description(item.getDescription())
                .available(item.getIsAvailable())
                .name(item.getName())
                .build();
    }

    public List<ItemDto> mapToDtoList(List<Item> items) {
        return items.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private long createId(Long itemId) {
        if (itemId == null || itemId <= 0) return ++ITEM_ID;
        if (itemId > ITEM_ID) ITEM_ID = itemId;
        return itemId;
    }
}