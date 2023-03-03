package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */
@Data
@Builder
public class Item {
    private Long itemId;
    @NotNull(message = "Не указан быть указан id владельца.")
    private Long owner;
    private String description;
    @NotNull(message = "Не указано имя предмета.")
    private String name;
    private Boolean isAvailable;
}