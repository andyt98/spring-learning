package com.example.demo.student;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        validateStudentEmail(student.getEmail());
        studentRepository.save(student);
    }


    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalArgumentException("Student with ID " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with ID " + studentId + " does not exist"));
        if (name != null && !name.isBlank() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && !email.isBlank() && !Objects.equals(student.getEmail(), email)) {
            validateStudentEmail(email);
            student.setEmail(email);
        }
    }

    private void validateStudentEmail(String email) {
        studentRepository.findStudentByEmail(email)
                .ifPresent(student -> {
                    throw new IllegalArgumentException("Student with email " + email + " already exists");
                });
    }
}
