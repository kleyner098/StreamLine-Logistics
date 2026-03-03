package com.microservice.inventory.e2e;

import org.springframework.boot.test.context.SpringBootTest;

import com.microservice.inventory.shared.MySQLTestContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest implements MySQLTestContainer {

}
