package io.plucen.controllers;

import static java.util.stream.Collectors.toList;

import io.plucen.entities.Student;
import io.plucen.services.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentsSearchController {
  private final StudentService studentService;

  @Autowired
  public StudentsSearchController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/studentsSearch")
  public List<Student> findByName(@RequestParam String name) {
    return studentService.index().stream()
        .filter(student -> student.getName().toLowerCase().contains(name.toLowerCase()))
        .collect(toList());
  }
}
