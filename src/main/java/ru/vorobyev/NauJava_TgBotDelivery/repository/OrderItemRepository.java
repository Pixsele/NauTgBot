package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderItemEntity;

/**
 * Репозиторий для управления элементами заказа системы.
 * <p>
 * Предоставляет базовые CRUD-операции для работы с сущностью {@link OrderItemEntity},
 */

@Repository
@RepositoryRestResource(path = "orderItem")
public interface OrderItemRepository extends CrudRepository<OrderItemEntity, Long> {
}
