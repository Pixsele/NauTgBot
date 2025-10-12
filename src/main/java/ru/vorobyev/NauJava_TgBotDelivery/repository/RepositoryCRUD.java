package ru.vorobyev.NauJava_TgBotDelivery.repository;

public interface RepositoryCRUD<T,ID> {
    void create(T entity);
    T read(ID id);
    void update(T entity);
    void delete(ID id);
}
