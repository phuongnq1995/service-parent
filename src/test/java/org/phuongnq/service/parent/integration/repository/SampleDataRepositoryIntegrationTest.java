package org.phuongnq.service.parent.integration.repository;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.phuongnq.service.parent.entity.SampleData;
import org.phuongnq.service.parent.repository.SampleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
@Import(TestContainersConfiguration.class)
public class SampleDataRepositoryIntegrationTest {

    @Autowired
    protected PostgreSQLContainer postgreSQLContainer;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    void test() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

    @Test
    void checkHealth() {
        when().get("/actuator/health")
                .then().statusCode(200)
                .and().body("status", containsString("UP"));
    }

    @Test
    void whenRequestingHobbits_thenReturnFrodoAndSam() {
        when().get("/api")
                .then().statusCode(200)
                .and().body("totalElements", greaterThan(0));
    }
}
