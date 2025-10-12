package ru.vorobyev.NauJava_TgBotDelivery.service;

import ru.vorobyev.NauJava_TgBotDelivery.entity.UserEntity;

public interface UserService {
    void createUser(Long telegramId, String username, String name, String lastname);
    UserEntity getEntityById(Long telegramId);
    void deleteUserById(Long telegramId);
    void updateUsername(Long telegramId, String username);
    void updateName(Long telegramId, String name);
    void updateLastname(Long telegramId, String lastname);
}
