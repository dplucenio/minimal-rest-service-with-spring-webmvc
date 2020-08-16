package io.plucen;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfiguration {

  @Bean
  public ObjectMapper getObjectMapper() {
    return new ObjectMapper();
  }

  //  @Getter private static final StudentRepository studentRepository = new
  // MemoryStudentRepository();
  //
  //  @Getter
  //  private static final StudentService studentService = new StudentService(studentRepository);
  //
  //  @Getter
  //  private static final StudentsController studentsController =
  //      new StudentsController(studentService);
  //
  //  @Getter private static final DashboardController dashBoardController = new
  // DashboardController();
}
