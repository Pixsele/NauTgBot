package ru.vorobyev.NauJava_TgBotDelivery.entity;

/**
 * Сущность пользователя Telegram, содержащая основную информацию (telegramId, никнейм, имя, фамилию).
 * Используется для представления пользователей внутри системы доставки.
 */

public class UserEntity {

    //telegramId
    private Long id;
    private String username;
    private String name;
    private String lastname;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String name, String lastname) {
        this.id = id;
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
