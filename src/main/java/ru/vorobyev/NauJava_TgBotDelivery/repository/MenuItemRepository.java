package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vorobyev.NauJava_TgBotDelivery.entity.MenuItemEntity;

/**
 * Репозиторий для управления позициями в меню предприятий системы.
 * <p>
 * Предоставляет базовые CRUD-операции для работы с сущностью {@link MenuItemEntity},
 */

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItemEntity, Long> {
}
