package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vorobyev.NauJava_TgBotDelivery.entity.CourierEntity;

/**
 * Репозиторий для управления курьерами системы.
 * <p>
 * Предоставляет базовые CRUD-операции для работы с сущностью {@link CourierEntity},
 */

@Repository
public interface CourierRepository extends CrudRepository<CourierEntity,Long> {
}
