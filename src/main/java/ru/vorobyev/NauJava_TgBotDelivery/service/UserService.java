package ru.vorobyev.NauJava_TgBotDelivery.service;

import ru.vorobyev.NauJava_TgBotDelivery.entity.UserEntity;

/**
 * Сервис для управления пользователями Telegram в системе доставки.
 *
 * <p>Определяет основные операции CRUD и обновление отдельных полей пользователей.
 * Сервис отвечает за бизнес-логику работы с объектами {@link UserEntity} и
 * делегирует хранение данных соответствующим репозиториям.
 */

public interface UserService {
    void createUser(Long telegramId, String username, String name, String lastname);
    UserEntity getEntityById(Long telegramId);
    void deleteUserById(Long telegramId);
    void updateUsername(Long telegramId, String username);
    void updateName(Long telegramId, String name);
    void updateLastname(Long telegramId, String lastname);
}
