package com.microservice.inventory.shared;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;



public interface MySQLTestContainer {
    
    //@ServiceConnection
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    // La anotación @ServiceConnection se encarga de registrar automáticamente las propiedades 
    // necesarias para la conexión a la base de datos en Spring Boot, utilizando el puerto 
    // asignado por Docker. Esto elimina la necesidad de configurar manualmente las propiedades 
    // dinámicas con @DynamicPropertySource.

    // Registramos las propiedades dinámicamente (puerto aleatorio que asigne Docker)
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {

        if (!mysqlContainer.isRunning()) {
            mysqlContainer.start();
        }
        
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");
    }

}
