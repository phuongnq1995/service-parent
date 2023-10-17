package org.phuongnq.service.parent.integration.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.phuongnq.service.parent.entity.SampleData;
import org.phuongnq.service.parent.repository.SampleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@Testcontainers
@SpringBootTest(webEnvironment = DEFINED_PORT)
@DirtiesContext(classMode = AFTER_CLASS)
public class SampleDataRepositoryIntegrationTest {

    @Container
    @ServiceConnection
    public static GenericContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa")
            .withReuse(true);

    @Autowired
    SampleDataRepository repository;

    @BeforeEach
    void beforeEach() {
        repository.deleteAll();
    }

    @Test
    void whenRequestingHobbits_thenReturnFrodoAndSam() {
        repository.saveAll(List.of(
                SampleData.builder()
                        .id(UUID.fromString("e7b8ac10-e4b0-466d-b9a8-f48a23e22b92"))
                        .name("Sample1")
                        .build(),
                SampleData.builder()
                        .id(UUID.fromString("8bde912f-e912-4e94-b95c-7cc5d5847ece"))
                        .name("Sample2")
                        .build()
        ));

        when().get("/api")
                .then().statusCode(200)
                .and().body("name", hasItems("Sample1", "Sample2"));
    }
}
