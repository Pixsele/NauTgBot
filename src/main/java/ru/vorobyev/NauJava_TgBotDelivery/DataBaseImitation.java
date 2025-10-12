package ru.vorobyev.NauJava_TgBotDelivery;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.vorobyev.NauJava_TgBotDelivery.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Конфигурационный класс, имитирующий базу данных пользователей.
 *
 * <p>Предоставляет singleton-бин {@link List} для хранения объектов {@link UserEntity}
 * в памяти приложения. Используется для временного хранения пользователей
 * до интеграции с реальной базой данных.
 */

@Configuration
public class DataBaseImitation {
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public List<UserEntity> userEntityList() {
        return new ArrayList<>();
    }
}
