package edu.miu.cs425.patientappointmentbookingapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.miu.cs425.patientappointmentbookingapp.model.Patient;
import edu.miu.cs425.patientappointmentbookingapp.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository repo;
    private final ObjectMapper mapper;
    private static final DateTimeFormatter FLEX = DateTimeFormatter.ofPattern("yyyy-M-d");

    // Spring-এর pre-configured ObjectMapper inject করছি (JavaTimeModule auto-registered)
    public PatientService(PatientRepository repo, ObjectMapper mapper) {
        this.repo = repo;
        this.mapper = mapper.copy().enable(SerializationFeature.INDENT_OUTPUT);
    }

    // অ্যাসাইনমেন্টের টেবিল rows → Patient objects
    public void seedFromArray(List<String[]> rows) {
        rows.stream().map(r -> new Patient(
                parseLong(r[0]),
                nullIfBlank(r[1]),
                nullIfBlank(r[2]),
                nullIfBlank(r[3]),
                nullIfBlank(r[4]),
                nullIfBlank(r[5]),
                parseDate(r[6])
        )).forEach(p -> repo.saveAll(List.of(p)));
    }

    // বয়স Desc (Oldest → Youngest)
    public List<Patient> sortedByAgeDesc() {
        return repo.findAll().stream()
                .sorted(Comparator.comparingInt(Patient::getAge).reversed())
                .toList();
    }

    public String toJson(List<Patient> patients) {
        try {
            return mapper.writeValueAsString(patients);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON serialization failed", e);
        }
    }

    private static Long parseLong(String s){ return s==null||s.isBlank()? null: Long.parseLong(s.trim()); }
    private static String nullIfBlank(String s){ return (s==null||s.isBlank())? null: s.trim(); }
    private static LocalDate parseDate(String s){ return (s==null||s.isBlank())? null: LocalDate.parse(s.trim(), FLEX); }
}
