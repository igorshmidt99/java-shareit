package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */
@Data
@Builder
public class ItemDto {
    private Long id;
    @NotNull(message = "Не указано описание предмета.")
    private String description;
    @NotNull(message = "Не указано имя предмета.")
    private String name;
    private Boolean isAvailable;
}