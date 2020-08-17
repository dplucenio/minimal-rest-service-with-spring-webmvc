package io.plucen.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.plucen.entities.Student;
import io.plucen.services.StudentService;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RawStudentsController {

  private final StudentService studentService;
  private final ObjectMapper objectMapper;

  @Autowired
  public RawStudentsController(StudentService studentService, ObjectMapper objectMapper) {
    this.studentService = studentService;
    this.objectMapper = objectMapper;
  }

  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json; charset=UTF-8");
    response.getWriter().println(objectMapper.writeValueAsString(studentService.index()));
  }

  public void post(HttpServletRequest request, HttpServletResponse response) throws IOException {
    StringBuilder json = new StringBuilder();
    BufferedReader reader = request.getReader();
    String line;
    while ((line = reader.readLine()) != null) {
      json.append(line).append("\n");
    }

    Map<String, String> jsonMap = objectMapper.readValue(json.toString(), Map.class);
    Student student = studentService.create(jsonMap.get("name"));
    response.setContentType("application/json; charset=UTF-8");
    response.getWriter().println(objectMapper.writeValueAsString(student));
  }
}
