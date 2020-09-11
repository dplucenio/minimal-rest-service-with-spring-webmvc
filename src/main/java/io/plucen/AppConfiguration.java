package io.plucen;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan
@EnableWebMvc
public class AppConfiguration {

  @Bean
  public Flyway flyway(@Qualifier("PostgresDataSource") DataSource datasource) {
    return Flyway.configure().dataSource(datasource).load();
  }

  @Bean
  public JdbcTemplate getJdbcTemplate(@Qualifier("PostgresDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean("H2DataSource")
  public DataSource h2DataSource() {
    final JdbcDataSource dataSource = new JdbcDataSource();
    dataSource.setURL("jdbc:h2:mem:");
    return dataSource;
  }

  @Bean("PostgresDataSource")
  public DataSource postgresDataSource() {
    final PGSimpleDataSource dataSource = new PGSimpleDataSource();
    dataSource.setURL("jdbc:postgresql://localhost/postgres");
    dataSource.setUser("postgres");
    dataSource.setPassword("jdbc");
    return dataSource;
  }
}
