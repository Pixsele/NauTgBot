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

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final EntityManager em;

    @Autowired
    public OrderRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<OrderEntity> findByStatusAndAddress(OrderStatus status, String address) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);

        Root<OrderEntity> order = cq.from(OrderEntity.class);
        Predicate predicate = cb.equal(order.get("status"), status);
        Predicate predicate2 = cb.equal(order.get("address"), address);

        cq.select(order).where(cb.and(predicate, predicate2));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<OrderEntity> findDeliveredOrdersByCourierId(Long courierId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);

        Root<OrderEntity> order = cq.from(OrderEntity.class);
        Predicate predicateCourierId = cb.equal(order.get("courierId"), courierId);
        Predicate predicate = cb.equal(order.get("status"), OrderStatus.DELIVERED);

        cq.select(order).where(cb.and(predicate, predicateCourierId));

        return em.createQuery(cq).getResultList();
    }
}
