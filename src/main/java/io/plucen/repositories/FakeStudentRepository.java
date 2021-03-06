package io.plucen.repositories;

import io.plucen.entities.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class FakeStudentRepository implements StudentRepository {

  private final List<Student> students = new ArrayList<>();

  @Override
  public List<Student> index() {
    return List.copyOf(students);
  }

  @Override
  public Optional<Student> findById(UUID id) {
    return students.stream().filter(student -> student.getId().equals(id)).findAny();
  }

  @Override
  public void create(Student student) {
    students.add(student);
  }

  @Override
  public void delete(UUID id) {
    students.removeIf(student -> student.getId().equals(id));
  }
}
