package ru.vorobyev.NauJava_TgBotDelivery.repositoryOther;

import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderEntity;
import ru.vorobyev.NauJava_TgBotDelivery.entity.enums.OrderStatus;

import java.util.List;

public interface OrderRepositoryCustom {

    List<OrderEntity> findByStatusAndAddress(OrderStatus status, String address);

    List<OrderEntity> findDeliveredOrdersByCourierId(Long courierId);
}
