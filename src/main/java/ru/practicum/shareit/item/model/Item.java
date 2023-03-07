package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */
@Data
@Builder
public class Item {
    private Long itemId;
    @NotNull(message = "Не указан указан владец.")
    private User owner;
    private String description;
    @NotNull(message = "Не указано имя предмета.")
    private String name;
    private Boolean isAvailable;
}