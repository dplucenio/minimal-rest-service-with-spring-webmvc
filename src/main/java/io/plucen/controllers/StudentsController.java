package io.plucen.controllers;

import io.plucen.entities.Student;
import io.plucen.services.StudentService;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentsController {

  private final StudentService studentService;

  @Autowired
  public StudentsController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/students")
  public List<Student> index() {
    return studentService.index();
  }

  @PostMapping("/students")
  public void create(@RequestBody StudentCreationDTO student) {
    this.studentService.create(student.getName());
  }

  @Data
  private static class StudentCreationDTO {
    private String name;
  }
}
