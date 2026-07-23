package com.autoflow.Repository;

import com.autoflow.Entity.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MechanicRepository extends JpaRepository<Mechanic,Long> {
}
