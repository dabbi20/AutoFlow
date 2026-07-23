package com.autoflow.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "diagnoses")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "problem_description",nullable = false)
    private String problemDescription;

    @NotBlank
    @Column(name = "technical_notes",nullable = false)
    private String technicalNotes;

    @NotBlank
    @Column(name = "customer_message",nullable = false)
    private String customerMessage;
    @NotNull
    @PositiveOrZero
    @Column(name = "estimated_days",nullable = false)
    private Integer estimatedDays;
    @NotNull
    @PositiveOrZero
    @Column(
            name = "estimated_price",
            nullable = false,
            precision = 12,
            scale = 2
    )
    private BigDecimal estimatedPrice;
    @NotNull
    @Column(name = "is_final",nullable = false)
    private Boolean isFinal = false;
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

    @NotNull
    @OneToOne
    @JoinColumn(name = "service_order_id",nullable = false, unique = true)
    private ServiceOrder serviceOrder;

    public Diagnosis() {
    }

    public Diagnosis(Long id, String problemDescription, String technicalNotes, String customerMessage, Integer estimatedDays, BigDecimal estimatedPrice, Boolean isFinal,  ServiceOrder serviceOrder) {
        this.id = id;
        this.problemDescription = problemDescription;
        this.technicalNotes = technicalNotes;
        this.customerMessage = customerMessage;
        this.estimatedDays = estimatedDays;
        this.estimatedPrice = estimatedPrice;
        this.isFinal = isFinal != null ? isFinal : false;
        this.serviceOrder = serviceOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getTechnicalNotes() {
        return technicalNotes;
    }

    public void setTechnicalNotes(String technicalNotes) {
        this.technicalNotes = technicalNotes;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    public Integer getEstimatedDays() {
        return estimatedDays;
    }

    public void setEstimatedDays(Integer estimatedDays) {
        this.estimatedDays = estimatedDays;
    }

    public BigDecimal getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(BigDecimal estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public Boolean getFinal() {
        return isFinal;
    }

    public void setFinal(Boolean aFinal) {
        isFinal = aFinal;
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

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }
}
