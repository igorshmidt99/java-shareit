package ru.practicum.shareit.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dao.UserDao;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.exception.UserAlreadyExistException;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao storage;

    private final UserMapper mapper;

    @Override
    public UserDto add(UserDto userDto) {
        checkEmailOfNewUser(userDto);
        User user = mapper.mapToUser(userDto);
        storage.add(user);
        userDto = mapper.mapToDto(user);
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto, long userId) {
        if (userDto.getEmail() != null) checkEmailWhileUpdate(userDto, userId);
        User thisUser = storage.getById(userId);
        User updatedUser = mapper.mapToUpdatedUser(userDto, thisUser);
        storage.update(updatedUser);
        userDto = mapper.mapToDto(updatedUser);
        return userDto;
    }

    @Override
    public void delete(long userId) {
        storage.delete(userId);
    }

    @Override
    public UserDto getById(long userId) {
        User user = storage.getById(userId);

        return mapper.mapToDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = storage.getAll();

        return mapper.getDtos(users);
    }

    private void checkEmailWhileUpdate(UserDto userDto, long userId) {
        List<User> users = storage.getAll();

        if (users.size() > 0)
            users.stream()
                .filter(user -> user.getEmail().equals(userDto.getEmail()) && user.getId() != userId)
                .findAny()
                .ifPresent(user -> {
                    throw new UserAlreadyExistException(
                            String.format("Пользователь с %s существует.", user.getEmail())
                    );
                });
    }

    private void checkEmailOfNewUser(UserDto userDto) {
        List<User> users = storage.getAll();

        if (users.size() > 0)
            users.stream()
                    .filter(user -> user.getEmail().equals(userDto.getEmail()))
                    .findAny()
                    .ifPresent(user -> {
                        throw new UserAlreadyExistException(
                                String.format("Пользователь с %s существует.", user.getEmail())
                        );
                    });
    }

}