package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vorobyev.NauJava_TgBotDelivery.entity.EnterpriseEntity;

@Repository
public interface EnterpriseRepository extends CrudRepository<EnterpriseEntity,Long> {
}
