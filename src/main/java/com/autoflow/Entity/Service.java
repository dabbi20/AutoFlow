package com.autoflow.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "name",nullable = false,unique = true)
    private String name;
    @NotBlank
    @Column(name = "description",nullable = false)
    private String description;
    @NotNull
    @PositiveOrZero
    @Column(name = "base_price",nullable = false,precision = 12,
            scale = 2)
    private BigDecimal basePrice;
    @NotNull
    @PositiveOrZero
    @Column(name = "estimated_hours",nullable = false)
    private Integer estimatedHours;
    @Column(name = "active",nullable = false)
    private Boolean active = true;
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();

        if (this.createdAt == null) {
            this.createdAt = now;
        }

        if (this.updatedAt == null) {
            this.updatedAt = now;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "service")

    private Set<ServiceOrderService> serviceOrderServices = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj){return  true;}
        if (!(obj instanceof Service service)){
            return false;
        }
        return name != null && name.equals(service.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Service() {
    }

    public Service(Long id, String name, String description, BigDecimal basePrice, Integer estimatedHours, Boolean active, Set<ServiceOrderService>serviceOrderServices) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.estimatedHours = estimatedHours;
        this.active = active != null ? active : true;
        this.serviceOrderServices = serviceOrderServices;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<ServiceOrderService> getServiceOrderServices() {
        return serviceOrderServices;
    }

    public void setServiceOrderServices(Set<ServiceOrderService> serviceOrderServices) {
        this.serviceOrderServices = serviceOrderServices;
    }
}
