package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderEntity;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    List<OrderEntity> findByStatusAndAddress(String status, RabbitConnectionDetails.Address address);

    @Query("SELECT o FROM OrderEntity o WHERE o.courier.id = :courierId and o.status = 'DELIVERED'")
    List<OrderEntity> findDeliveredOrdersByCourierId(@Param("courierId")Long courierId);
}

