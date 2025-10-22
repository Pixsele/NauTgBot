package ru.vorobyev.NauJava_TgBotDelivery;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vorobyev.NauJava_TgBotDelivery.entity.CourierEntity;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderEntity;
import ru.vorobyev.NauJava_TgBotDelivery.entity.enums.CourierStatus;
import ru.vorobyev.NauJava_TgBotDelivery.entity.enums.OrderStatus;
import ru.vorobyev.NauJava_TgBotDelivery.repository.OrderRepository;
import ru.vorobyev.NauJava_TgBotDelivery.repositoryOther.OrderRepositoryImpl;

import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
class OrderRepositoryTests {

    private final OrderRepository orderRepository;
    private final OrderRepositoryImpl orderRepositoryImpl;
    private final EntityManager em;

    @Autowired
    OrderRepositoryTests(OrderRepository orderRepository,
                         OrderRepositoryImpl orderRepositoryImpl,
                         EntityManager em) {
        this.orderRepository = orderRepository;
        this.orderRepositoryImpl = orderRepositoryImpl;
        this.em = em;
    }


    /**
     * Проверка успешного поиска заказа по статусу и адресу.
     */
    @Test
    @DisplayName("Позитивный: findByStatusAndAddress JPQL возвращает заказ")
    void testFindByStatusAndAddress_Positive() {
        String address = "Addr_" + UUID.randomUUID();
        OrderEntity order = createOrder(address, OrderStatus.CREATED, null);
        orderRepository.save(order);

        List<OrderEntity> found = orderRepository.findByStatusAndAddress(order.getStatus().name(), address);

        Assertions.assertFalse(found.isEmpty(), "Список не должен быть пустым");
        Assertions.assertEquals(address, found.getFirst().getAddress());
        Assertions.assertEquals(OrderStatus.CREATED, found.getFirst().getStatus());
    }

    /**
     * Проверка, что метод не возвращает результатов для несуществующего адреса.
     */
    @Test
    @DisplayName("Негативный: findByStatusAndAddress JPQL возвращает пустой список")
    void testFindByStatusAndAddress_Negative() {
        List<OrderEntity> found = orderRepository.findByStatusAndAddress("CREATED", "NoSuchAddress");

        Assertions.assertTrue(found.isEmpty(), "Список должен быть пустым");
    }

    /**
     * Проверка поиска доставленных заказов по идентификатору курьера.
     */
    @Test
    @DisplayName("Позитивный: findDeliveredOrdersByCourierId JPQL возвращает заказ")
    void testFindDeliveredOrdersByCourierId_Positive() {
        CourierEntity courier = createAndPersistCourier();
        OrderEntity order = createOrder("Delivered_" + UUID.randomUUID(), OrderStatus.DELIVERED, courier);
        orderRepository.save(order);

        List<OrderEntity> found = orderRepository.findDeliveredOrdersByCourierId(courier.getId());

        Assertions.assertFalse(found.isEmpty());
        Assertions.assertEquals(OrderStatus.DELIVERED, found.getFirst().getStatus());
        Assertions.assertEquals(courier.getId(), found.getFirst().getCourier().getId());
    }

    /**
     * Проверка пустого результата при отсутствии доставленных заказов.
     */
    @Test
    @DisplayName("Негативный: findDeliveredOrdersByCourierId JPQL возвращает пустой список")
    void testFindDeliveredOrdersByCourierId_Negative() {
        CourierEntity courier = createAndPersistCourier();

        List<OrderEntity> found = orderRepository.findDeliveredOrdersByCourierId(courier.getId());

        Assertions.assertTrue(found.isEmpty());
    }


    /**
     * Проверка успешного поиска с использованием Criteria API.
     */
    @Test
    @DisplayName("Позитивный: findByStatusAndAddress Criteria возвращает заказ")
    void testFindByStatusAndAddress_Criteria_Positive() {
        String address = "CritAddr_" + UUID.randomUUID();
        OrderEntity order = createOrder(address, OrderStatus.CREATED, null);
        em.persist(order);

        List<OrderEntity> found = orderRepositoryImpl.findByStatusAndAddress(OrderStatus.CREATED.name(), address);

        Assertions.assertFalse(found.isEmpty());
        Assertions.assertEquals(address, found.getFirst().getAddress());
    }

    /**
     * Проверка отсутствия результатов при несуществующем адресе (Criteria)
     */
    @Test
    @DisplayName("Негативный: findByStatusAndAddress Criteria возвращает пустой список")
    void testFindByStatusAndAddress_Criteria_Negative() {
        List<OrderEntity> found = orderRepositoryImpl.findByStatusAndAddress(OrderStatus.CREATED.name(), "NoSuchAddress");

        Assertions.assertTrue(found.isEmpty());
    }

    /**
     * Проверка поиска доставленных заказов по курьеру через Criteria API.
     */
    @Test
    @DisplayName("Позитивный: findDeliveredOrdersByCourierId Criteria возвращает заказ")
    void testFindDeliveredOrdersByCourierId_Criteria_Positive() {
        CourierEntity courier = createAndPersistCourier();
        OrderEntity order = createOrder("Crit_" + UUID.randomUUID(), OrderStatus.DELIVERED, courier);
        em.persist(order);

        List<OrderEntity> found = orderRepositoryImpl.findDeliveredOrdersByCourierId(courier.getId());

        Assertions.assertFalse(found.isEmpty());
        Assertions.assertEquals(OrderStatus.DELIVERED, found.getFirst().getStatus());
    }

    /**
     * Проверка отсутствия доставленных заказов по курьеру (Criteria)
     */
    @Test
    @DisplayName("Негативный: findDeliveredOrdersByCourierId Criteria возвращает пустой список")
    void testFindDeliveredOrdersByCourierId_Criteria_Negative() {
        CourierEntity courier = createAndPersistCourier();

        List<OrderEntity> found = orderRepositoryImpl.findDeliveredOrdersByCourierId(courier.getId());

        Assertions.assertTrue(found.isEmpty());
    }


    private OrderEntity createOrder(String address, OrderStatus status, CourierEntity courier) {
        OrderEntity order = new OrderEntity();
        order.setAddress(address);
        order.setStatus(status);
        order.setCourier(courier);
        return order;
    }

    private CourierEntity createAndPersistCourier() {
        CourierEntity courier = new CourierEntity(
                "Courier_" + UUID.randomUUID(),
                "123456",
                CourierStatus.AVAILABLE,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
        em.persist(courier);
        return courier;
    }
}
