package com.autoflow.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ServiceOrderId implements Serializable {
    @Column(name = "service_order_id")
    private Long serviceOrderId;
    @Column(name = "service_id")
    private Long serviceId;

    public ServiceOrderId(){}
    public ServiceOrderId(
            Long serviceOrderId,
            Long serviceId
    ) {
        this.serviceOrderId = serviceOrderId;
        this.serviceId = serviceId;
    }

    public Long getServiceOrderId(){
        return  serviceOrderId;
    }
    public Long getServiceId(){
        return serviceId;
    }

    public void setServiceOrderId(Long serviceOrderId){
        this.serviceOrderId = serviceOrderId;
    }

    public void setServiceId(Long serviceId){
        this.serviceId = serviceId;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj){return true;}
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ServiceOrderId that = (ServiceOrderId)obj;

        return Objects.equals(serviceOrderId, that.serviceOrderId) && Objects.equals(serviceId,that.serviceId);
    }

    @Override
    public int hashCode() {
        return  Objects.hash(serviceId,serviceOrderId);
    }
}
