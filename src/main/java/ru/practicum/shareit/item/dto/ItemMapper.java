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
                .isAvailable(itemDto.getIsAvailable())
                .name(itemDto.getName())
                .owner(userId)
                .build();
        long newItemId = createId(item.getItemId());
        item.setItemId(newItemId);
        return item;
    }

    public Item mapToItem(ItemDto itemDto, long userId, long itemId) {
        createId(itemId);
        return Item.builder()
                .description(itemDto.getDescription())
                .isAvailable(itemDto.getIsAvailable())
                .name(itemDto.getName())
                .owner(userId)
                .itemId(itemId)
                .build();
    }

    public ItemDto mapToDto(Item item) {
        return ItemDto.builder()
                .id(item.getItemId())
                .description(item.getDescription())
                .isAvailable(item.getIsAvailable())
                .name(item.getName())
                .build();
    }

    public List<ItemDto> mapToDtoList(List<Item> items) {
        return items.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private long createId(long itemId) {
        if (itemId <= 0) return ++ITEM_ID;
        if (itemId > ITEM_ID) ITEM_ID = itemId;
        return itemId;
    }
}