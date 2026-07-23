package com.autoflow.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "service_advisors")
public class ServiceAdvisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "full_name",nullable = false,length = 100)
    private String fullName;
    @Column(name = "phone",nullable = false,unique = true,length = 20)
    private String phone;
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

    @OneToMany(mappedBy = "serviceAdvisor")
    private Set<ServiceOrder> serviceOrders = new HashSet<>();
    @OneToOne
    @JoinColumn(
            name = "user_account_id",
            nullable = false,
            unique = true
    )
    private UserAccount userAccount;

    @OneToMany(mappedBy = "receivedBy")
    private Set<Payment>receivedPayments = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof  ServiceAdvisor serviceAdvisor)){
            return false;
        }
        return phone != null && phone.equals(serviceAdvisor.phone);
    }

    @Override
    public int hashCode() {
        return phone != null ? phone.hashCode() : 0;
    }

    public ServiceAdvisor(){}

    public ServiceAdvisor(Long id, String fullName, String phone, Boolean active, UserAccount userAccount) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.active = active != null ? active : true;
        this.userAccount = userAccount;
    }

    public Set<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Set<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public Set<Payment> getReceivedPayments() {
        return receivedPayments;
    }

    public void setReceivedPayments(Set<Payment> receivedPayments) {
        this.receivedPayments = receivedPayments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    //METODOS AUXILIARES
    public void addServiceOrder(ServiceOrder serviceOrder) {
        serviceOrders.add(serviceOrder);
        serviceOrder.setServiceAdvisor(this);
    }

    public void addReceivedPayment(Payment payment) {
        receivedPayments.add(payment);
        payment.setReceivedBy(this);
    }
}
