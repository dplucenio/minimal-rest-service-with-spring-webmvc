package io.plucen.services;

import io.plucen.entities.Student;
import io.plucen.repositories.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public Student create(String name) {
    Student student = new Student(UUID.randomUUID(), name);
    studentRepository.create(student);
    return student;
  }

  public List<Student> index() {
    return studentRepository.index();
  }

  public Optional<Student> findById(UUID id) {
    return studentRepository.findById(id);
  }
}
