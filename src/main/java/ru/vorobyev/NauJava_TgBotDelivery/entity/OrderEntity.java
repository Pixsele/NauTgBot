package ru.vorobyev.NauJava_TgBotDelivery.entity;

import jakarta.persistence.*;
import ru.vorobyev.NauJava_TgBotDelivery.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Сущность заказа.
 * <p>
 * Содержит основную информацию о заказе, включая статус, адрес доставки,
 * пользователя, предприятие, курьера и временные метки создания и доставки.
 */

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private EnterpriseEntity enterprise;

    @ManyToOne
    private CourierEntity courier;

    private BigDecimal delivery_lat;
    private BigDecimal delivery_lon;

    private LocalDateTime created_at;
    private LocalDateTime delivered_at;

    public OrderEntity() {
    }

    public OrderEntity(OrderStatus status, String address, UserEntity user, EnterpriseEntity enterprise, CourierEntity courier, BigDecimal delivery_lat, BigDecimal delivery_lon, LocalDateTime created_at, LocalDateTime delivered_at) {
        this.status = status;
        this.address = address;
        this.user = user;
        this.enterprise = enterprise;
        this.courier = courier;
        this.delivery_lat = delivery_lat;
        this.delivery_lon = delivery_lon;
        this.created_at = created_at;
        this.delivered_at = delivered_at;
    }

    public OrderEntity(Long id, OrderStatus status, String address, UserEntity user, EnterpriseEntity enterprise, CourierEntity courier, BigDecimal delivery_lat, BigDecimal delivery_lon, LocalDateTime created_at, LocalDateTime delivered_at) {
        this.id = id;
        this.status = status;
        this.address = address;
        this.user = user;
        this.enterprise = enterprise;
        this.courier = courier;
        this.delivery_lat = delivery_lat;
        this.delivery_lon = delivery_lon;
        this.created_at = created_at;
        this.delivered_at = delivered_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public EnterpriseEntity getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseEntity enterprise) {
        this.enterprise = enterprise;
    }

    public CourierEntity getCourier() {
        return courier;
    }

    public void setCourier(CourierEntity courier) {
        this.courier = courier;
    }

    public BigDecimal getDelivery_lat() {
        return delivery_lat;
    }

    public void setDelivery_lat(BigDecimal delivery_lat) {
        this.delivery_lat = delivery_lat;
    }

    public BigDecimal getDelivery_lon() {
        return delivery_lon;
    }

    public void setDelivery_lon(BigDecimal delivery_lon) {
        this.delivery_lon = delivery_lon;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getDelivered_at() {
        return delivered_at;
    }

    public void setDelivered_at(LocalDateTime delivered_at) {
        this.delivered_at = delivered_at;
    }
}
