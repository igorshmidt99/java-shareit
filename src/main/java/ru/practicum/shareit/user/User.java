package ru.practicum.shareit.user;

import lombok.Data;

import javax.validation.constraints.Email;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class User {
    private Long id;
    private String name;
    @Email(message = "Неверный формат email")
    private String email;
}