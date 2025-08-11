package edu.miu.cs425.patientappointmentbookingapp.repository;

import edu.miu.cs425.patientappointmentbookingapp.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepository {
    private final List<Patient> store = new ArrayList<>();

    public void saveAll(List<Patient> patients) {
        store.addAll(patients);
    }

    public List<Patient> findAll() {
        return new ArrayList<>(store);
    }
}
