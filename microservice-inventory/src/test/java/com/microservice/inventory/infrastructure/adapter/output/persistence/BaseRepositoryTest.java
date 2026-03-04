package com.microservice.inventory.infrastructure.adapter.output.persistence;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;

import com.microservice.inventory.shared.MySQLTestContainer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class BaseRepositoryTest implements MySQLTestContainer{

    @AfterEach
    void cleanUp(@Autowired JdbcTemplate jdbcTemplate) {
        // Esto asegura que cada test empiece con la DB limpia
        jdbcTemplate.execute("TRUNCATE TABLE products"); 
        jdbcTemplate.execute("TRUNCATE TABLE stocks"); 
    }
    
}
