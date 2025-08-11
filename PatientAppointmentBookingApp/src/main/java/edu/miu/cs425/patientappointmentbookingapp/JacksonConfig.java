package edu.miu.cs425.patientappointmentbookingapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // Spring context-এ ObjectMapper bean রেজিস্টার করছি
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());                  // LocalDate/Time সাপোর্ট
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // ISO-8601 string আউটপুট
        return mapper;
    }
}
