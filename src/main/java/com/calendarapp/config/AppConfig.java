package com.calendarapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableJdbcRepositories(basePackages = "com.calendarapp.repository")
public class AppConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    // @Value("${spring.datasource.url}")
    // private String url;

    // @Value("${spring.datasource.username}")
    // private String username;

    // @Value("${spring.datasource.password}")
    // private String password;

    // @Value("${spring.datasource.driver-class-name}")
    // private String driverClassName;

    // @Bean
    // public DriverManagerDataSource dataSource() {
    //     DriverManagerDataSource dataSource = new DriverManagerDataSource();
    //     dataSource.setDriverClassName(driverClassName);
    //     dataSource.setUrl(url);
    //     dataSource.setUsername(username);
    //     dataSource.setPassword(password);
    //     return dataSource;
    // }

    // @Bean
    // public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    //     return new JdbcTemplate(dataSource);
    // }
}
