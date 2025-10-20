package ru.vorobyev.NauJava_TgBotDelivery.repositoryOther;

import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderEntity;

import java.util.List;

/**
 * Пользовательский интерфейс для дополнительных методов репозитория заказов.
 */

public interface OrderRepositoryCustom {

    /**
     * Ищет заказы по статусу и адресу доставки.
     *
     * @param status  статус заказа
     * @param address адрес доставки
     * @return список найденных заказов
     */

    List<OrderEntity> findByStatusAndAddress(String status, String address);

    /**
     * Возвращает список заказов, которые были доставлены определённым курьером.
     *
     * @param courierId идентификатор курьера
     * @return список доставленных заказов
     */

    List<OrderEntity> findDeliveredOrdersByCourierId(Long courierId);
}
