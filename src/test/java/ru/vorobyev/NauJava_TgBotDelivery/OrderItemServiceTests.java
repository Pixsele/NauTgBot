package ru.vorobyev.NauJava_TgBotDelivery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.vorobyev.NauJava_TgBotDelivery.entity.MenuItemEntity;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderEntity;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderItemEntity;
import ru.vorobyev.NauJava_TgBotDelivery.entity.enums.OrderStatus;
import ru.vorobyev.NauJava_TgBotDelivery.repository.MenuItemRepository;
import ru.vorobyev.NauJava_TgBotDelivery.repository.OrderItemRepository;
import ru.vorobyev.NauJava_TgBotDelivery.repository.OrderRepository;
import ru.vorobyev.NauJava_TgBotDelivery.service.OrderItemService;

import java.util.List;

@SpringBootTest
@Transactional
class OrderItemServiceTests {

    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    OrderItemServiceTests(OrderItemService orderItemService, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderItemService = orderItemService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Test
    void testCreateOrderItemWithOrder_Positive() {
        OrderEntity order = new OrderEntity();
        order.setAddress("AddressTest");
        order.setStatus(OrderStatus.CREATED);

        MenuItemEntity menuItem = new MenuItemEntity();
        menuItem.setName("MenuItemTest");
        menuItemRepository.save(menuItem);

        OrderItemEntity item1 = new OrderItemEntity();
        OrderItemEntity item2 = new OrderItemEntity();

        item1.setCount(1);
        item2.setCount(1);
        item1.setMenuItem(menuItem);
        item2.setMenuItem(menuItem);

        List<OrderItemEntity> items = List.of(item1, item2);

        List<OrderItemEntity> savedItems = orderItemService.createOrderItemWithOrder(items, order);

        Assertions.assertEquals(2, savedItems.size());
        Assertions.assertNotNull(savedItems.getFirst().getOrder().getId());
        Assertions.assertTrue(orderRepository.findById(savedItems.getFirst().getOrder().getId()).isPresent());
    }

    @Test
    void testCreateOrderItemWithOrder_Negative_RollbackOnInvalidCount() {
        long countTempOrder = orderRepository.count();
        long countTempOrderItem = orderItemRepository.count();

        OrderEntity order = new OrderEntity();
        order.setAddress("Address1");

        MenuItemEntity menuItem = new MenuItemEntity();
        menuItem.setId(1L);
        menuItem.setName("MenuItemTest");

        OrderItemEntity validItem = new OrderItemEntity();
        validItem.setMenuItem(menuItem);
        validItem.setCount(2);

        OrderItemEntity invalidItem = new OrderItemEntity();
        invalidItem.setMenuItem(menuItem);
        invalidItem.setCount(-1);

        List<OrderItemEntity> items = List.of(validItem, invalidItem);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
            orderItemService.createOrderItemWithOrder(items, order)
        );

        Assertions.assertEquals(countTempOrder, orderRepository.count());
        Assertions.assertEquals(countTempOrderItem, orderItemRepository.count());
    }
}
