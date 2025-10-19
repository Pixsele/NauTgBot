package ru.vorobyev.NauJava_TgBotDelivery.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderEntity;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderItemEntity;
import ru.vorobyev.NauJava_TgBotDelivery.repository.OrderItemRepository;
import ru.vorobyev.NauJava_TgBotDelivery.repository.OrderRepository;

import java.util.List;

/**
 * Сервисный класс для работы с заказами и их позициями (товарами).
 * <p>
 * Отвечает за создание заказов вместе с элементами заказа,
 * а также обеспечивает транзакционность операций сохранения.
 */

@Service
public class OrderItemService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * Создает новый заказ и связанные с ним позиции заказа в одной транзакции.
     * <p>
     * Если хотя бы один элемент имеет некорректное количество (меньше или равно нулю),
     * выбрасывается исключение {@link IllegalArgumentException}, и транзакция откатывается.
     *
     * @param orderItemEntityList список элементов заказа, которые необходимо сохранить
     * @param orderEntity         объект заказа, к которому будут привязаны элементы
     * @return список сохраненных элементов заказа, связанных с новым заказом
     * @throws IllegalArgumentException если количество любого элемента заказа меньше или равно нулю
     */

    @Transactional
    public List<OrderItemEntity> createOrderItemWithOrder(List<OrderItemEntity> orderItemEntityList, OrderEntity orderEntity) {
        for(OrderItemEntity orderItemEntity : orderItemEntityList) {
            if(orderItemEntity.getCount() <= 0) {
                throw new IllegalArgumentException();
            }
        }

        OrderEntity newOrderEntity = orderRepository.save(orderEntity);

        for(OrderItemEntity orderItemEntity : orderItemEntityList) {
            orderItemEntity.setOrder(newOrderEntity);
        }

        orderItemRepository.saveAll(orderItemEntityList);
        return orderItemEntityList;
    }
}
