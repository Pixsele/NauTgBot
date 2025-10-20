package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vorobyev.NauJava_TgBotDelivery.entity.UserEntity;


/**
 * Репозиторий для управления пользователями системы.
 * <p>
 * Предоставляет базовые CRUD-операции для работы с сущностью {@link UserEntity},
 */

@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Long> {
}
