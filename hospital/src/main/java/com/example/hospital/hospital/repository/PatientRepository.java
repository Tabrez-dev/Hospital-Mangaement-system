// PatientRepository.java
package com.example.hospital.hospital.repository;

import com.example.hospital.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // You can add custom query methods if needed
}
