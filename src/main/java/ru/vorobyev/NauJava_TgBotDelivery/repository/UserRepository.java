package ru.vorobyev.NauJava_TgBotDelivery.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vorobyev.NauJava_TgBotDelivery.entity.UserEntity;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class UserRepository implements RepositoryCRUD<UserEntity,Long> {

    private final ArrayList<UserEntity> users;

    @Autowired
    public UserRepository(ArrayList<UserEntity> users) {
        this.users = users;
    }

    @Override
    public void create(UserEntity entity) {
        users.add(entity);
    }

    @Override
    public UserEntity read(Long aLong) {
        for (UserEntity user : users) {
            if (Objects.equals(user.getId(), aLong)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void update(UserEntity entity) {
        for(int i=0; i<users.size(); i++){
            if(Objects.equals(users.get(i).getId(), entity.getId())){
                users.set(i, entity);
            }
        }
    }

    @Override
    public void delete(Long aLong) {
        users.removeIf(user -> Objects.equals(user.getId(), aLong));
    }
}
