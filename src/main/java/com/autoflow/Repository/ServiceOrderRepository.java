package com.autoflow.Repository;

import com.autoflow.Entity.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder,Long> {
}
