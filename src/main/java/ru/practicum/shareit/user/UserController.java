package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{userId}")
    public UserDto getUserById(@Size(min = 1) @PathVariable Long userId) {
        return service.getById(userId);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return service.getAll();
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return service.add(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@RequestBody UserDto userDto, @Size(min = 1) @PathVariable Long userId) {
        return service.update(userDto, userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@Size(min = 1) @PathVariable Long userId) {
        service.delete(userId);
    }

}