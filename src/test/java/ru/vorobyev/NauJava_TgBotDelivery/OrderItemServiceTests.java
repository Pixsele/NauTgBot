package ru.vorobyev.NauJava_TgBotDelivery;

import org.junit.jupiter.api.*;
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

/**
 * Тесты для проверки корректности создания OrderItem вместе с Order.
 */
@SpringBootTest
@Transactional
class OrderItemServiceTests {

    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    OrderItemServiceTests(OrderItemService orderItemService,
                          OrderRepository orderRepository,
                          OrderItemRepository orderItemRepository) {
        this.orderItemService = orderItemService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * Тест проверяет успешное создание заказа с несколькими позициями меню.
     */
    @Test
    @DisplayName("Позитивный тест: создание заказа с двумя OrderItem")
    void testCreateOrderItemWithOrder_Positive() {
        OrderEntity order = createOrder("AddressTest", OrderStatus.CREATED);
        MenuItemEntity menuItem = createAndSaveMenuItem("MenuItemTest");

        OrderItemEntity item1 = createOrderItem(menuItem, 1);
        OrderItemEntity item2 = createOrderItem(menuItem, 1);

        List<OrderItemEntity> items = List.of(item1, item2);

        List<OrderItemEntity> savedItems = orderItemService.createOrderItemWithOrder(items, order);

        Assertions.assertEquals(2, savedItems.size(), "Ожидается сохранение двух OrderItem");
        Assertions.assertNotNull(savedItems.getFirst().getOrder().getId(), "У заказа должен быть ID");
        Assertions.assertTrue(orderRepository.findById(savedItems.getFirst().getOrder().getId()).isPresent(), "Заказ должен быть сохранён в базе");
    }

    /**
     * Тест проверяет откат транзакции при недопустимом количестве OrderItem.
     */
    @Test
    @DisplayName("Негативный тест: rollback при отрицательном количестве")
    void testCreateOrderItemWithOrder_Negative_RollbackOnInvalidCount() {
        long countTempOrder = orderRepository.count();
        long countTempOrderItem = orderItemRepository.count();

        OrderEntity order = createOrder("Address1", null);
        MenuItemEntity menuItem = createMenuItemWithoutSaving("MenuItemTest");

        OrderItemEntity validItem = createOrderItem(menuItem, 2);
        OrderItemEntity invalidItem = createOrderItem(menuItem, -1);

        List<OrderItemEntity> items = List.of(validItem, invalidItem);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                        orderItemService.createOrderItemWithOrder(items, order),
                "Ожидается исключение IllegalArgumentException при отрицательном количестве"
        );

        Assertions.assertEquals(countTempOrder, orderRepository.count(), "Заказы не должны быть сохранены");
        Assertions.assertEquals(countTempOrderItem, orderItemRepository.count(), "Позиции заказа не должны быть сохранены");
    }


    private OrderEntity createOrder(String address, OrderStatus status) {
        OrderEntity order = new OrderEntity();
        order.setAddress(address);
        order.setStatus(status);
        return order;
    }

    private MenuItemEntity createAndSaveMenuItem(String name) {
        MenuItemEntity menuItem = new MenuItemEntity();
        menuItem.setName(name);
        return menuItemRepository.save(menuItem);
    }

    private MenuItemEntity createMenuItemWithoutSaving(String name) {
        MenuItemEntity menuItem = new MenuItemEntity();
        menuItem.setName(name);
        return menuItem;
    }

    private OrderItemEntity createOrderItem(MenuItemEntity menuItem, int count) {
        OrderItemEntity item = new OrderItemEntity();
        item.setMenuItem(menuItem);
        item.setCount(count);
        return item;
    }
}
