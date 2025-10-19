package ru.vorobyev.NauJava_TgBotDelivery.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderEntity;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderItemEntity;
import ru.vorobyev.NauJava_TgBotDelivery.repository.OrderItemRepository;
import ru.vorobyev.NauJava_TgBotDelivery.repository.OrderRepository;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public List<OrderItemEntity> createOrderItemWithOrder(List<OrderItemEntity> orderItemEntityList, OrderEntity orderEntity) {
        OrderEntity newOrderEntity = orderRepository.save(orderEntity);

        orderItemEntityList.forEach(orderItemEntity -> {
            orderItemEntity.setOrder(newOrderEntity);
            orderItemRepository.save(orderItemEntity);
        });

        return orderItemEntityList;
    }
}
