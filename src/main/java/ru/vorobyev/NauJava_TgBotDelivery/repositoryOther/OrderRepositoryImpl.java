package ru.vorobyev.NauJava_TgBotDelivery.repositoryOther;


import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderEntity;
import ru.vorobyev.NauJava_TgBotDelivery.entity.enums.OrderStatus;

import java.util.List;

/**
 * Реализация пользовательских запросов к репозиторию заказов.
 * <p>
 * Использует {@link EntityManager} и {@link jakarta.persistence.criteria.CriteriaQuery}
 * для построения гибких динамических запросов.
 */

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final EntityManager em;

    @Autowired
    public OrderRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * Возвращает список заказов по указанным статусу и адресу доставки.
     * @param status  статус заказа (строковое значение, например, "CREATED" или "DELIVERED")
     * @param address адрес доставки заказа
     * @return список заказов, удовлетворяющих условиям
     */

    @Override
    public List<OrderEntity> findByStatusAndAddress(String status, String address) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);

        Root<OrderEntity> order = cq.from(OrderEntity.class);
        Predicate predicate = cb.equal(order.get("status"), status);
        Predicate predicate2 = cb.equal(order.get("address"), address);

        cq.select(order).where(cb.and(predicate, predicate2));

        return em.createQuery(cq).getResultList();
    }

    /**
     * Возвращает список заказов, доставленных указанным курьером.
     *
     * @param courierId идентификатор курьера
     * @return список доставленных заказов, выполненных данным курьером
     */

    @Override
    public List<OrderEntity> findDeliveredOrdersByCourierId(Long courierId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);

        Root<OrderEntity> order = cq.from(OrderEntity.class);
        Predicate predicateCourierId = cb.equal(order.get("courier").get("id"), courierId);
        Predicate predicate = cb.equal(order.get("status"), OrderStatus.DELIVERED);

        cq.select(order).where(cb.and(predicate, predicateCourierId));

        return em.createQuery(cq).getResultList();
    }
}
