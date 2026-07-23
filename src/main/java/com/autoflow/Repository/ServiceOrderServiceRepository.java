package com.autoflow.Repository;

import com.autoflow.Entity.ServiceOrderId;
import com.autoflow.Entity.ServiceOrderService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOrderServiceRepository extends JpaRepository<ServiceOrderService, ServiceOrderId> {
}
