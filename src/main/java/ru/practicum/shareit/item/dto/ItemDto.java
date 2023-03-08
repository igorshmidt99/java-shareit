package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Не указано имя предмета.")
    private String name;
    @NotNull(message = "Не указан доступен ли предмет.")
    private Boolean available;
}