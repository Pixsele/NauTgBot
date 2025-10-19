package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vorobyev.NauJava_TgBotDelivery.entity.UserEntity;

@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Long> {
}
