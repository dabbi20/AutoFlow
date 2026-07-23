package com.autoflow.Entity;

import com.autoflow.Enums.MechanicStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mechanics")
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "full_name",nullable = false,length = 100)
    private String name;
    @Column(name = "phone",nullable = false,unique = true,length = 20)
    private String phone;
    @Column(name = "specialty",nullable = false,length = 60)
    private String specialty;
    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status",nullable = false)
    private MechanicStatus mechanicStatus = MechanicStatus.AVAILABLE;
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

    @OneToMany(mappedBy = "mechanic")
    private Set<ServiceOrder> serviceOrders = new HashSet<>();
    @OneToOne
    @JoinColumn(
            name = "user_account_id",
            nullable = false,
            unique = true
    )
    private UserAccount userAccount;

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Mechanic that)){
            return false;
        }

        return phone != null && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return phone != null ? phone.hashCode() : 0;
    }

    public Mechanic(){}

    public Mechanic(Long id, String name, String phone, String specialty, MechanicStatus mechanicStatus, Boolean active, UserAccount userAccount) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.specialty = specialty;
        this.mechanicStatus = mechanicStatus != null
                ? mechanicStatus
                : MechanicStatus.AVAILABLE;

        this.active = active != null
                ? active
                : true;
        this.userAccount = userAccount;
    }

    public Set<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Set<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public MechanicStatus getMechanicStatus() {
        return mechanicStatus;
    }

    public void setMechanicStatus(MechanicStatus mechanicStatus) {
        this.mechanicStatus = mechanicStatus;
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

    public void addServiceOrder(ServiceOrder serviceOrder){
        serviceOrders.add(serviceOrder);
        serviceOrder.setMechanic(this);
    }

    public void removeServiceOrder(ServiceOrder serviceOrder) {
        serviceOrders.remove(serviceOrder);
        serviceOrder.setMechanic(null);
    }
}
