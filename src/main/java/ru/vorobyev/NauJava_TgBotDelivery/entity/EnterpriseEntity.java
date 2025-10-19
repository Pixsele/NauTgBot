package ru.vorobyev.NauJava_TgBotDelivery.entity;


import jakarta.persistence.*;
import ru.vorobyev.NauJava_TgBotDelivery.entity.enums.EnterpriseStatus;
import ru.vorobyev.NauJava_TgBotDelivery.entity.enums.EnterpriseType;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "enterprises")
public class EnterpriseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnterpriseType type;

    private LocalTime workStart;
    private LocalTime workEnd;

    @Enumerated(EnumType.STRING)
    private EnterpriseStatus status = EnterpriseStatus.OPEN;

    @Column(nullable = false)
    private BigDecimal enterprise_lat;
    @Column(nullable = false)
    private BigDecimal enterprise_lon;

    public EnterpriseEntity() {
    }

    public EnterpriseEntity(String name, String address, EnterpriseType type, LocalTime workStart, LocalTime workEnd, EnterpriseStatus status, BigDecimal enterprise_lat, BigDecimal enterprise_lon) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.status = status;
        this.enterprise_lat = enterprise_lat;
        this.enterprise_lon = enterprise_lon;
    }

    public EnterpriseEntity(Long id, String name, String address, EnterpriseType type, LocalTime workStart, LocalTime workEnd, EnterpriseStatus status, BigDecimal enterprise_lat, BigDecimal enterprise_lon) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.status = status;
        this.enterprise_lat = enterprise_lat;
        this.enterprise_lon = enterprise_lon;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EnterpriseType getType() {
        return type;
    }

    public void setType(EnterpriseType type) {
        this.type = type;
    }

    public LocalTime getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalTime workStart) {
        this.workStart = workStart;
    }

    public LocalTime getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(LocalTime workEnd) {
        this.workEnd = workEnd;
    }

    public EnterpriseStatus getStatus() {
        return status;
    }

    public void setStatus(EnterpriseStatus status) {
        this.status = status;
    }

    public BigDecimal getEnterprise_lat() {
        return enterprise_lat;
    }

    public void setEnterprise_lat(BigDecimal enterprise_lat) {
        this.enterprise_lat = enterprise_lat;
    }

    public BigDecimal getEnterprise_lon() {
        return enterprise_lon;
    }

    public void setEnterprise_lon(BigDecimal enterprise_lon) {
        this.enterprise_lon = enterprise_lon;
    }
}
