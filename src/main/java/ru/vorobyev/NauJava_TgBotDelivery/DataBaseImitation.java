package ru.vorobyev.NauJava_TgBotDelivery;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.vorobyev.NauJava_TgBotDelivery.entity.UserEntity;

import java.util.ArrayList;


@Configuration
public class DataBaseImitation {
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ArrayList<UserEntity> userEntityList() {
        return new ArrayList<UserEntity>();
    }
}
