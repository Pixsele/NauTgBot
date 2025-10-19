package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vorobyev.NauJava_TgBotDelivery.entity.CourierEntity;

@Repository
public interface CourierRepository extends CrudRepository<CourierEntity,Long> {
}
