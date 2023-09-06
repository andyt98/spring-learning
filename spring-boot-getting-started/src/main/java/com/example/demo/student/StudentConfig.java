package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student alex = new Student("Alex", "alex@gmail.com",
                    LocalDate.of(1995, Month.JANUARY, 23));

            studentRepository.saveAll(List.of(alex));
        };
    }
}
