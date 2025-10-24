package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.vorobyev.NauJava_TgBotDelivery.entity.EnterpriseEntity;

/**
 * Репозиторий для управления предприятиями системы.
 * <p>
 * Предоставляет базовые CRUD-операции для работы с сущностью {@link EnterpriseEntity},
 */

@Repository
@RepositoryRestResource(path = "enterprise")
public interface EnterpriseRepository extends CrudRepository<EnterpriseEntity,Long> {
}
