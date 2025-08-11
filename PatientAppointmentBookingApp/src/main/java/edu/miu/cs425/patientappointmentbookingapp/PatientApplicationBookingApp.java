package edu.miu.cs425.patientappointmentbookingapp;

import edu.miu.cs425.patientappointmentbookingapp.service.PatientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PatientApplicationBookingApp implements CommandLineRunner {

    private final PatientService patientService;

    @Value("${app.env:DEFAULT}")
    private String env;

    public PatientApplicationBookingApp(PatientService patientService) {
        this.patientService = patientService; // constructor DI
    }

    public static void main(String[] args) {
        SpringApplication.run(PatientApplicationBookingApp.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(">>> Running profile/env: " + env);

        var rows = List.of(
                new String[]{"1","Daniel","Agar","(641) 123-0009","dagar@m.as","1 N Street","1987-1-19"},
                new String[]{"2","Ana","Smith",null,"amsith@te.edu",null,"1948-12-5"},
                new String[]{"3","Marcus","Garvey","(123) 292-0018",null,"4 East Ave","2001-9-18"},
                new String[]{"4","Jeff","Goldbloom","(999) 165-1192","jgold@es.co.za",null,"1995-2-28"},
                new String[]{"5","Mary","Washington",null,null,"30 W Burlington","1932-5-31"}
        );

        patientService.seedFromArray(rows);
        var sorted = patientService.sortedByAgeDesc();
        var json = patientService.toJson(sorted);
        System.out.println(json);  // ✅ age সহ JSON (Oldest → Youngest)
    }
}
