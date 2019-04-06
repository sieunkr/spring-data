package com.example.demo;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://ip:3306/cafe?serverTimezone=UTC");
        ds.setPassword("password");
        ds.setUsername("user");
        //ds.setDriverClassName("com.mysql.jdbc.Driver");
        return ds;
    }

}
