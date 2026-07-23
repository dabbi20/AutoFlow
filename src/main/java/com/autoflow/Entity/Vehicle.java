package com.autoflow.Entity;

import com.autoflow.Enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "plate",nullable = false,unique = true,length = 20)
    private String plate;
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type",nullable = false)
    private VehicleType vehicleType;
    @Column(name = "brand",nullable = false,length = 50)
    private String brand;
    @Column(name = "model",nullable = false,length = 50)
    private String model;
    @Min(value = 1886, message = "El año del modelo debe ser igual o posterior a 1886")
    @Column(name = "model_year",nullable = false)
    private Integer modelYear;
    @Column(name = "color",nullable = false,length = 80)
    private String color;
    @Column(name = "chassis_number",nullable = false,unique = true,length = 50)
    private String chassisNumber;
    @Column(name = "engine_number",nullable = false,unique = true,length = 50)
    private String engineNumber;
    @Min(value = 0, message = "El kilometraje no puede ser menor a cero")
    @Column(name = "mileage",nullable = false)
    private Integer mileage;
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

    @OneToMany(mappedBy = "vehicle")
    private Set<ServiceOrder> serviceOrders = new HashSet<>();

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    private Customer customer;


    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Vehicle other)){
            return false;
        }
        return plate != null && plate.equals(other.plate);
    }

    @Override
    public int hashCode() {
        return plate != null ? plate.hashCode() : 0;
    }

    public Vehicle(){}
    public Vehicle(Long id,String plate,VehicleType vehicleType,String brand,String model,Integer modelYear,String color,String chassisNumber,String engineNumber,Integer mileage, Customer customer){
        this.id = id;
        this.plate = plate;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.modelYear = modelYear;
        this.color = color;
        this.chassisNumber = chassisNumber;
        this.engineNumber = engineNumber;
        this.mileage = mileage;

        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public String getColor() {
        return color;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public Integer getMileage() {
        return mileage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Set<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //METODOS AUXILIARES

    public void addServiceOrder(ServiceOrder serviceOrder) {
        serviceOrders.add(serviceOrder);
        serviceOrder.setVehicle(this);
    }

    public void removeServiceOrder(ServiceOrder serviceOrder) {
        serviceOrders.remove(serviceOrder);
        serviceOrder.setVehicle(null);
    }
}
