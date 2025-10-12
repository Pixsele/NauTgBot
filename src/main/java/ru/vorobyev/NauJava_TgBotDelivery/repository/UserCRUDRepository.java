package ru.vorobyev.NauJava_TgBotDelivery.repository;

/**
 * Базовый интерфейс CRUD-операций над сущностями пользователей.
 *
 * <p>Определяет общий контракт для создания, чтения, обновления и удаления.
 *
 * @param <T>  тип сущности, с которой работает репозиторий
 * @param <ID> тип идентификатора сущности
 */

public interface UserCRUDRepository<T,ID> {
    void create(T entity);
    T read(ID id);
    void update(T entity);
    void delete(ID id);
}
