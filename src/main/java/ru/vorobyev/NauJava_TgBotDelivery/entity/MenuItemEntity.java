package ru.vorobyev.NauJava_TgBotDelivery.entity;


import jakarta.persistence.*;
import ru.vorobyev.NauJava_TgBotDelivery.entity.enums.MenuItemStatus;

/**
 * Сущность элемента меню предприятия.
 * <p>
 * Содержит информацию о названии, статусе блюда и предприятии, которому принадлежит.
 */

@Entity
@Table(name = "menu_items")
public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MenuItemStatus status = MenuItemStatus.UNKNOWN;

    @ManyToOne
    private EnterpriseEntity enterprise;

    public MenuItemEntity() {
    }

    public MenuItemEntity(String name, MenuItemStatus status, EnterpriseEntity enterprise) {
        this.name = name;
        this.status = status;
        this.enterprise = enterprise;
    }

    public MenuItemEntity(Long id, String name, MenuItemStatus status, EnterpriseEntity enterprise) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.enterprise = enterprise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuItemStatus getStatus() {
        return status;
    }

    public void setStatus(MenuItemStatus status) {
        this.status = status;
    }

    public EnterpriseEntity getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseEntity enterprise) {
        this.enterprise = enterprise;
    }
}
