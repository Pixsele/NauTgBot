package ru.vorobyev.NauJava_TgBotDelivery;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vorobyev.NauJava_TgBotDelivery.entity.*;
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
    OrderRepositoryTests(OrderRepository orderRepository, OrderRepositoryImpl orderRepositoryImpl, EntityManager em) {
        this.orderRepository = orderRepository;
        this.orderRepositoryImpl = orderRepositoryImpl;
        this.em = em;
    }

    @Test
    void testFindByStatusAndAddress_Positive() {
        String address = "Addr_" + UUID.randomUUID();
        OrderEntity order = new OrderEntity();
        order.setStatus(OrderStatus.CREATED);
        order.setAddress(address);
        orderRepository.save(order);

        List<OrderEntity> found = orderRepository.findByStatusAndAddress(order.getStatus().name(), address);
        Assertions.assertFalse(found.isEmpty());
        Assertions.assertEquals(address, found.getFirst().getAddress());
        Assertions.assertEquals(OrderStatus.CREATED, found.getFirst().getStatus());
    }

    @Test
    void testFindByStatusAndAddress_Negative() {
        List<OrderEntity> found = orderRepository.findByStatusAndAddress("CREATED", "NoSuchAddress");
        Assertions.assertTrue(found.isEmpty());
    }

    @Test
    void testFindDeliveredOrdersByCourierId_Positive() {
        CourierEntity courier = new CourierEntity("Courier_" + UUID.randomUUID(), "123456", CourierStatus.AVAILABLE, BigDecimal.ZERO, BigDecimal.ZERO);
        em.persist(courier);

        OrderEntity order = new OrderEntity();
        order.setStatus(OrderStatus.DELIVERED);
        order.setAddress("Delivered_" + UUID.randomUUID());
        order.setCourier(courier);
        orderRepository.save(order);

        List<OrderEntity> found = orderRepository.findDeliveredOrdersByCourierId(courier.getId());
        Assertions.assertFalse(found.isEmpty());
        Assertions.assertEquals(OrderStatus.DELIVERED, found.getFirst().getStatus());
        Assertions.assertEquals(courier.getId(), found.getFirst().getCourier().getId());
    }

    @Test
    void testFindDeliveredOrdersByCourierId_Negative() {
        CourierEntity courier = new CourierEntity("EmptyCourier_" + UUID.randomUUID(), "999999", CourierStatus.AVAILABLE, BigDecimal.ZERO, BigDecimal.ZERO);
        em.persist(courier);

        List<OrderEntity> found = orderRepository.findDeliveredOrdersByCourierId(courier.getId());
        Assertions.assertTrue(found.isEmpty());
    }

    @Test
    void testFindByStatusAndAddress_Criteria_Positive() {
        String address = "CritAddr_" + UUID.randomUUID();
        OrderEntity order = new OrderEntity();
        order.setStatus(OrderStatus.CREATED);
        order.setAddress(address);
        em.persist(order);

        List<OrderEntity> found = orderRepositoryImpl.findByStatusAndAddress(OrderStatus.CREATED.name(), address);
        Assertions.assertFalse(found.isEmpty());
        Assertions.assertEquals(address, found.getFirst().getAddress());
    }

    @Test
    void testFindByStatusAndAddress_Criteria_Negative() {
        List<OrderEntity> found = orderRepositoryImpl.findByStatusAndAddress(OrderStatus.CREATED.name(), "NoSuchAddress");
        Assertions.assertTrue(found.isEmpty());
    }

    @Test
    void testFindDeliveredOrdersByCourierId_Criteria_Positive() {
        CourierEntity courier = new CourierEntity("CourierCrit_" + UUID.randomUUID(), "222333", CourierStatus.AVAILABLE, BigDecimal.ONE, BigDecimal.ONE);
        em.persist(courier);

        OrderEntity order = new OrderEntity();
        order.setCourier(courier);
        order.setStatus(OrderStatus.DELIVERED);
        order.setAddress("Crit_" + UUID.randomUUID());
        em.persist(order);

        List<OrderEntity> found = orderRepositoryImpl.findDeliveredOrdersByCourierId(courier.getId());
        Assertions.assertFalse(found.isEmpty());
        Assertions.assertEquals(OrderStatus.DELIVERED, found.getFirst().getStatus());
    }

    @Test
    void testFindDeliveredOrdersByCourierId_Criteria_Negative() {
        CourierEntity courier = new CourierEntity("EmptyCrit_" + UUID.randomUUID(), "000000", CourierStatus.AVAILABLE, BigDecimal.ONE, BigDecimal.ONE);
        em.persist(courier);

        List<OrderEntity> found = orderRepositoryImpl.findDeliveredOrdersByCourierId(courier.getId());
        Assertions.assertTrue(found.isEmpty());
    }
}
