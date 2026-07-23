package com.autoflow.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Entity
@Table(name = "service_order_services")
public class ServiceOrderService {

  @EmbeddedId
  private ServiceOrderId id;

  @NotNull
  @ManyToOne
  @MapsId("serviceOrderId")
  @JoinColumn(name = "service_order_id", nullable = false)
  private ServiceOrder serviceOrder;

  @NotNull
  @ManyToOne
  @MapsId("serviceId")
  @JoinColumn(name = "service_id", nullable = false)
  private Service service;

  @NotNull
  @PositiveOrZero
  @Column(name = "agreed_price", nullable = false,precision = 12,
          scale = 2)
  private BigDecimal agreedPrice;

  @NotNull
  @Positive
  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @NotBlank
  @Column(name = "notes", nullable = false)
  private String notes;

  public ServiceOrderService() {
  }

  public ServiceOrderService(
          ServiceOrderId id,
          ServiceOrder serviceOrder,
          Service service,
          BigDecimal agreedPrice,
          Integer quantity,
          String notes
  ) {
    this.id = id;
    this.serviceOrder = serviceOrder;
    this.service = service;
    this.agreedPrice = agreedPrice;
    this.quantity = quantity;
    this.notes = notes;
  }

  public ServiceOrderId getId() {
    return id;
  }

  public void setId(ServiceOrderId id) {
    this.id = id;
  }

  public ServiceOrder getServiceOrder() {
    return serviceOrder;
  }

  public void setServiceOrder(ServiceOrder serviceOrder) {
    this.serviceOrder = serviceOrder;
  }

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public BigDecimal getAgreedPrice() {
    return agreedPrice;
  }

  public void setAgreedPrice(BigDecimal agreedPrice) {
    this.agreedPrice = agreedPrice;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}