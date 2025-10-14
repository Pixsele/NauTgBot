package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vorobyev.NauJava_TgBotDelivery.entity.UserEntity;

import java.util.List;
import java.util.Objects;

/**
 * Репозиторий для управления пользователями системы доставки.
 *
 * <p>Реализует базовые CRUD-операции над объектами {@link UserEntity},
 * используя список, хранящийся в памяти приложения.
 */

@Component
public class UserRepository implements UserCRUDRepository<UserEntity,Long> {

    private final List<UserEntity> users;

    @Autowired
    public UserRepository(List<UserEntity> users) {
        this.users = users;
    }

    @Override
    public void create(UserEntity entity) {
        users.add(entity);
    }

    @Override
    public UserEntity read(Long aLong) {
        return users.stream().filter(user -> Objects.equals(user.getId(), aLong)).findFirst().orElse(null);
    }

    @Override
    public void update(UserEntity entity) {
        users.stream()
                .filter(user -> Objects.equals(user.getId(), entity.getId()))
                .findFirst().ifPresent(user -> {users.set(users.indexOf(user), entity);});
    }

    @Override
    public void delete(Long aLong) {
        users.removeIf(user -> Objects.equals(user.getId(), aLong));
    }
}
