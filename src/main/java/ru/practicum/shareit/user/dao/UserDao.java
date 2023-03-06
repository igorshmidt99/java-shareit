package ru.practicum.shareit.user.dao;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserDao {
    User add(User user);
    User update(User user);
    void delete(long userId);
    User getById(long userId);
    List<User> getAll();
}