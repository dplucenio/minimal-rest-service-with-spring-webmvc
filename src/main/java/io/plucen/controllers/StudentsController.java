package io.plucen.controllers;

import io.plucen.AppException;
import io.plucen.entities.Student;
import io.plucen.services.StudentService;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public List<Student> index() throws AppException {
    return studentService.index();
  }

  @GetMapping("/students/{idString}")
  public Student findById(@PathVariable String idString) throws AppException {
    UUID id;
    try {
      id = UUID.fromString(idString);
    } catch (IllegalArgumentException exception) {
      throw new AppException("invalid id", HttpStatus.BAD_REQUEST);
    }
    return studentService
        .findById(id)
        .orElseThrow(
            () ->
                new AppException(
                    String.format("There is no user with id %s", id), HttpStatus.BAD_REQUEST));
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
