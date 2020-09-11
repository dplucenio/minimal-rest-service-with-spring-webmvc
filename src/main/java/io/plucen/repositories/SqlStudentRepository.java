package io.plucen.repositories;

import io.plucen.entities.Student;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@Repository
@Primary
public class SqlStudentRepository implements StudentRepository {
  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<Student> index() {

    return jdbcTemplate.query(
        "select id, name from student",
        (resultSet, i) -> new Student((UUID) resultSet.getObject(1), resultSet.getString(2)));
  }

  @Override
  public Optional<Student> findById(UUID id) {
    Student query =
        jdbcTemplate.queryForObject(
            "select id, name from student where id = ?;",
            (resultSet, i) -> new Student(id, resultSet.getString(2)),
            id);
    return Optional.ofNullable(query);
  }

  @Override
  public void create(Student student) {
    jdbcTemplate.update(
        "insert into student(id, name) values(?, ?);", student.getId(), student.getName());
  }

  @Override
  public void delete(UUID id) {
    jdbcTemplate.update("delete from student where id = ?", id);
  }
}
