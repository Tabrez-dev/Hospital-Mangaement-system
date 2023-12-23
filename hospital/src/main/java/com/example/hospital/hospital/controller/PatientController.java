// PatientController.java
package com.example.hospital.hospital.controller;

import com.example.hospital.hospital.entity.Patient;
import com.example.hospital.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Patient> updateAdmissionStatus(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        return patientService.getPatientById(id)
                .map(existingPatient -> {
                    existingPatient.setStatus(updatedPatient.getStatus());
                    patientService.savePatient(existingPatient);
                    return ResponseEntity.ok(existingPatient);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/covid-status")
    public ResponseEntity<Patient> updateCovidStatus(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        return patientService.getPatientById(id)
                .map(existingPatient -> {
                    existingPatient.setCovidStatus(updatedPatient.getCovidStatus());
                    patientService.savePatient(existingPatient);
                    return ResponseEntity.ok(existingPatient);
                })
                .orElse(ResponseEntity.notFound().build());
    }
   

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatient(patient);
        return ResponseEntity.ok(savedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
