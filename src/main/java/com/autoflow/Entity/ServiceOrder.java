package com.autoflow.Entity;

import com.autoflow.Enums.OrderStatus;
import com.autoflow.Enums.PriorityType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "service_orders")
public class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Size(max = 30)
    @Column(
            name = "order_number",
            nullable = false,
            unique = true,
            length = 30
    )
    private String orderNumber;
    @NotBlank
    @Column(name = "reason_for_visit", nullable = false)
    private String reasonVisit;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus = OrderStatus.RECEIVED;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private PriorityType priorityType = PriorityType.MEDIUM;
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "closed_at")
    private LocalDateTime closedAt;

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

    @OneToOne(mappedBy = "serviceOrder")
    private Invoice invoice;

    @Override
    public boolean equals(Object obj) {
        if (this == obj){return true;}
        if (!(obj instanceof ServiceOrder other)){
            return false;
        }
        return orderNumber != null && orderNumber.equals(other.orderNumber);
    }

    @Override
    public int hashCode() {
        return orderNumber != null ? orderNumber.hashCode() : 0;
    }

    public ServiceOrder(){}

    public ServiceOrder(Long id, String orderNumber, String reasonVisit, OrderStatus orderStatus, PriorityType priorityType,  LocalDateTime closedAt, Set<ServiceOrderService> serviceOrderServices, Customer customer, Vehicle vehicle, Mechanic mechanic, ServiceAdvisor serviceAdvisor) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.reasonVisit = reasonVisit;
        this.orderStatus = orderStatus != null
                ? orderStatus
                : OrderStatus.RECEIVED;

        this.priorityType = priorityType != null
                ? priorityType
                : PriorityType.MEDIUM;

        this.closedAt = closedAt;
        this.serviceOrderServices = serviceOrderServices;
        this.customer = customer;
        this.vehicle = vehicle;
        this.mechanic = mechanic;
        this.serviceAdvisor = serviceAdvisor;
    }




    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getReasonVisit() {
        return reasonVisit;
    }

    public void setReasonVisit(String reasonVisit) {
        this.reasonVisit = reasonVisit;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public ServiceAdvisor getServiceAdvisor() {
        return serviceAdvisor;
    }

    public void setServiceAdvisor(ServiceAdvisor serviceAdvisor) {
        this.serviceAdvisor = serviceAdvisor;
    }

    @OneToMany(mappedBy = "serviceOrder")
    private Set<ServiceOrderService> serviceOrderServices = new HashSet<>();
    @OneToOne(mappedBy = "serviceOrder")
    private Diagnosis diagnosis;



    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    private  Customer customer;

    @ManyToOne
    @JoinColumn(
            name = "vehicle_id",
            nullable = false
    )
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(
            name = "mechanic_id",
            nullable = false
    )
    private Mechanic mechanic;

    @ManyToOne
    @JoinColumn(
            name = "advisor_id",
            nullable = false
    )
    private ServiceAdvisor serviceAdvisor;

}
