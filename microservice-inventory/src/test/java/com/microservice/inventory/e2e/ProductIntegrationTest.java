package com.microservice.inventory.e2e;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class ProductIntegrationTest extends BaseIntegrationTest{

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Crear un nuevo producto")
    public void testCreateProduct() {
        File createProductJson = new File("src/test/java/com/microservice/inventory/resources/payload/createProduct.json");


        given()
            .contentType(ContentType.JSON)
            .body(createProductJson)
        .when()
            .post("api/v1/products")
        .then()
            .statusCode(201)
            .body("sku", is("prueba"));
    }
}
