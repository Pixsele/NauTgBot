package ru.vorobyev.NauJava_TgBotDelivery.entity;

import jakarta.persistence.*;

/**
 * Сущность элемента заказа.
 * <p>
 * Представляет отдельную позицию в заказе, содержащую информацию о конкретном блюде и его количестве.
 * Связана с {@link OrderEntity} (многие к одному) и {@link MenuItemEntity} (многие к одному).
 */

@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrderEntity order;

    @ManyToOne
    private MenuItemEntity menuItem;

    @Column(nullable = false)
    private int count = 1;

    public OrderItemEntity() {
    }

    public OrderItemEntity(OrderEntity order, MenuItemEntity menuItem, int count) {
        this.order = order;
        this.menuItem = menuItem;
        this.count = count;
    }

    public OrderItemEntity(Long id, OrderEntity order, MenuItemEntity menuItem, int count) {
        this.id = id;
        this.order = order;
        this.menuItem = menuItem;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public MenuItemEntity getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItemEntity menuItem) {
        this.menuItem = menuItem;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
