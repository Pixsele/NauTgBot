package ru.vorobyev.NauJava_TgBotDelivery.entity;

import jakarta.persistence.*;

/**
 * Сущность пользователя Telegram, содержащая основную информацию (telegramId, никнейм, имя, фамилию).
 * Используется для представления пользователей внутри системы доставки.
 */
@Entity
@Table(name="users")
public class UserEntity {

    //telegramId
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname;

    public UserEntity() {
    }

    public UserEntity(String username, String name, String lastname) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
    }

    public UserEntity(Long telegramId, String username, String name, String lastname) {
        this.id=telegramId;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", username=" + username + ", name=" + name + "]";
    }
}
