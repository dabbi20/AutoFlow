package com.autoflow.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "full_name",nullable = false,length = 150)
    private String fullName;
    @Column(name = "phone",nullable = false,unique = true,length = 30)
    private String phone;
    @Column(name = "document_number",nullable = false,unique = true,length = 20)
    private String documentNumber;
    @Column(name = "address",nullable = false,length = 90)
    private String address;
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
    @OneToMany(mappedBy = "customer")
    private Set<ServiceOrder> serviceOrders = new HashSet<>();

    @OneToOne
    @JoinColumn(
            name = "user_account_id",
            nullable = false,
            unique = true
    )
    private UserAccount userAccount;


@OneToMany(mappedBy = "customer")
private Set<Vehicle>vehicles = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Customer otro)){
            return false;
        }
        return documentNumber != null
                && documentNumber.equals(otro.documentNumber);
    }

    @Override
    public int hashCode() {
        return documentNumber != null ? documentNumber.hashCode() : 0;
    }

    public Customer(){}

    public Customer(Long id, String fullName, String phone, String documentNumber, String address, UserAccount userAccount) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.documentNumber = documentNumber;
        this.address = address;
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

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getAddress() {
        return address;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setId(Long id){
      this.id = id;
    }

    public void setFullName(String fullName){
      this.fullName = fullName;
    }
    public void setPhone(String phone){
      this.phone = phone;
    }
    public void setDocumentNumber(String documentNumber){
      this.documentNumber = documentNumber;
    }
    public void setAddress(String address){
      this.address = address;
    }
    public void setCreatedAt(LocalDateTime createdAt){
      this.createdAt = createdAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt){
      this.updatedAt = updatedAt;
    }
    public void setUserAccount(UserAccount userAccount){
      this.userAccount = userAccount;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    //METODOS AUXILIARES

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
        vehicle.setCustomer(this);
    }

    public void removeVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);
        vehicle.setCustomer(null);
    }

    public void addServiceOrder(ServiceOrder serviceOrder){
        serviceOrders.add(serviceOrder);
        serviceOrder.setCustomer(this);
    }

    public void removeServiceOrder(ServiceOrder serviceOrder){
        serviceOrders.remove(serviceOrder);
        serviceOrder.setCustomer(null);
    }


}
