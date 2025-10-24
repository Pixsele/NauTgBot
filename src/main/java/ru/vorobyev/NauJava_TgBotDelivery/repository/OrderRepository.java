package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderEntity;

import java.util.List;

/**
 * Репозиторий для работы с сущностями {@link OrderEntity}.
 * <p>
 * Расширяет {@link CrudRepository} и включает дополнительные методы поиска заказов
 * по статусу, адресу доставки и идентификатору курьера.
 */

@Repository
@RepositoryRestResource(path = "order")
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    /**
     * Возвращает список заказов по указанным статусу и адресу доставки.
     * @param status  статус заказа (например, "CREATED", "DELIVERED" и т. д.)
     * @param address адрес доставки
     * @return список заказов, удовлетворяющих условиям поиска
     */

    List<OrderEntity> findByStatusAndAddress(String status, String address);

    /**
     * Возвращает список заказов, доставленных определённым курьером.
     * @param courierId идентификатор курьера
     * @return список заказов со статусом {@code DELIVERED}, закреплённых за данным курьером
     */

    @Query("SELECT o FROM OrderEntity o WHERE o.courier.id = :courierId and o.status = 'DELIVERED'")
    List<OrderEntity> findDeliveredOrdersByCourierId(@Param("courierId")Long courierId);
}

