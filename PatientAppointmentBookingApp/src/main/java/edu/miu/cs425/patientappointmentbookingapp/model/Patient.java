package edu.miu.cs425.patientappointmentbookingapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.Period;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;    // optional
    private String email;    // optional
    private String address;  // optional

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    public Patient() {}

    public Patient(Long id, String firstName, String lastName, String phone,
                   String email, String address, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() { return id; } public void setId(Long id){ this.id=id; }
    public String getFirstName(){ return firstName; } public void setFirstName(String v){ this.firstName=v; }
    public String getLastName(){ return lastName; } public void setLastName(String v){ this.lastName=v; }
    public String getPhone(){ return phone; } public void setPhone(String v){ this.phone=v; }
    public String getEmail(){ return email; } public void setEmail(String v){ this.email=v; }
    public String getAddress(){ return address; } public void setAddress(String v){ this.address=v; }
    public LocalDate getDateOfBirth(){ return dateOfBirth; } public void setDateOfBirth(LocalDate v){ this.dateOfBirth=v; }

    @JsonProperty("age")
    public int getAge() {
        if (dateOfBirth == null) return 0;
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
