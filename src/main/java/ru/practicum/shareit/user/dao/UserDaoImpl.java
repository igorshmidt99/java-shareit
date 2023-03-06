package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.exception.UserNotFoundException;
import ru.practicum.shareit.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private final Map<Long, User> users = new HashMap<>(1000);

    @Override
    public User add(User user) {
        return users.putIfAbsent(user.getId(), user);
    }

    @Override
    public User update(User user) {
        long userId = user.getId();

        checkAvailability(userId);
        return users.put(userId, user);
    }

    @Override
    public void delete(long userId) {
        checkAvailability(userId);
        users.remove(userId);
    }

    @Override
    public User getById(long userId) {
        checkAvailability(userId);
        return users.get(userId);
    }

    @Override
    public List<User> getAll() {
        return List.copyOf(users.values());
    }

    private void checkAvailability(long userId) {
        if (!users.containsKey(userId))
            throw new UserNotFoundException(String.format("Пользовватель с ID# %d не найден", userId));
    }

}