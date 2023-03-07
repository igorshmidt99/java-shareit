package ru.practicum.shareit.user.dto;

import lombok.Getter;
import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class UserMapper {
    private static long USER_ID;

    private UserMapper() { }

    public static User mapToUser(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .id(userDto.getId())
                .email(userDto.getEmail())
                .build();
        Long userId = createId(user.getId());
        user.setId(userId);
        return user;
    }

    public static User mapToUpdatedUser(UserDto userDto, User user) {
        String dtoEmail = userDto.getEmail();
        String dtoName = userDto.getName();
        if (dtoEmail != null) user.setEmail(dtoEmail);
        if (dtoName != null) user.setName(dtoName);
        return user;
    }

    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public static List<UserDto> getDtos(List<User> users) {
        return users.stream()
                .map(UserMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private static long createId(Long userId) {
        if (userId == null || userId <= 0) return ++USER_ID;
        if (userId > USER_ID) USER_ID = userId;
        return userId;
    }
}