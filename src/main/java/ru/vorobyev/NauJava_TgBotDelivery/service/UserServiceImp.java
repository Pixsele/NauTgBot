package ru.vorobyev.NauJava_TgBotDelivery.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vorobyev.NauJava_TgBotDelivery.AppConfig;
import ru.vorobyev.NauJava_TgBotDelivery.entity.UserEntity;
import ru.vorobyev.NauJava_TgBotDelivery.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final AppConfig appConfig;

    @Autowired
    public UserServiceImp(UserRepository userRepository,AppConfig appConfig) {
        this.userRepository = userRepository;
        this.appConfig = appConfig;
    }

    @Override
    public void createUser(Long telegramId, String username, String name, String lastname) {
        if(userRepository.read(telegramId) != null) {
            throw new IllegalArgumentException("User with id " + telegramId + " already exists");
        }

        UserEntity newUser = new UserEntity();

        newUser.setId(telegramId);
        newUser.setUsername(username);
        newUser.setName(name);
        newUser.setLastname(lastname);

        userRepository.create(newUser);
    }

    @Override
    public UserEntity getEntityById(Long telegramId) {
        UserEntity user = userRepository.read(telegramId);
        if(user == null) {
            throw new IllegalArgumentException("User with id " + telegramId + " does not exist");
        }
        return user;
    }

    @Override
    public void deleteUserById(Long telegramId) {
        if(userRepository.read(telegramId) == null) {
            throw new IllegalArgumentException("User with id " + telegramId + " does not exist");
        }
        userRepository.delete(telegramId);
    }

    @Override
    public void updateUsername(Long telegramId, String username) {
        UserEntity user = userRepository.read(telegramId);
        if(user == null) {
            throw new IllegalArgumentException("User with id " + telegramId + " does not exist");
        }
        user.setUsername(username);
        userRepository.update(user);
    }

    @Override
    public void updateName(Long telegramId, String name) {
        UserEntity user = userRepository.read(telegramId);
        if(user == null) {
            throw new IllegalArgumentException("User with id " + telegramId + " does not exist");
        }
        user.setName(name);
        userRepository.update(user);
    }

    @Override
    public void updateLastname(Long telegramId, String lastname) {
        UserEntity user = userRepository.read(telegramId);
        if(user == null) {
            throw new IllegalArgumentException("User with id " + telegramId + " does not exist");
        }
        user.setLastname(lastname);
        userRepository.update(user);
    }

    @PostConstruct
    public void printApp(){
        System.out.println("App name:" + appConfig.getAppName());
        System.out.println("App Version:" + appConfig.getAppVersion());
    };
}
