package ru.vorobyev.NauJava_TgBotDelivery.entity;


import jakarta.persistence.*;
import ru.vorobyev.NauJava_TgBotDelivery.entity.enums.CourierStatus;

import java.math.BigDecimal;

@Entity
@Table(name = "couriers")
public class CourierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private CourierStatus status = CourierStatus.AVAILABLE;

    private BigDecimal courier_lat;
    private BigDecimal courier_lon;

    public CourierEntity() {
    }

    public CourierEntity(String name, String phone, CourierStatus status, BigDecimal courier_lat, BigDecimal courier_lon) {
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.courier_lat = courier_lat;
        this.courier_lon = courier_lon;
    }

    public CourierEntity(Long id, String name, String phone, CourierStatus status, BigDecimal courier_lat, BigDecimal courier_lon) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.courier_lat = courier_lat;
        this.courier_lon = courier_lon;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CourierStatus getStatus() {
        return status;
    }

    public void setStatus(CourierStatus status) {
        this.status = status;
    }

    public BigDecimal getCourier_lat() {
        return courier_lat;
    }

    public void setCourier_lat(BigDecimal courier_lat) {
        this.courier_lat = courier_lat;
    }

    public BigDecimal getCourier_lon() {
        return courier_lon;
    }

    public void setCourier_lon(BigDecimal courier_lon) {
        this.courier_lon = courier_lon;
    }
}
