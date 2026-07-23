package com.autoflow.Repository;

import com.autoflow.Entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {
}
