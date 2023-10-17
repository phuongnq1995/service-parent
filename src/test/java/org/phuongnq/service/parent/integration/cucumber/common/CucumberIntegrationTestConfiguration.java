package org.phuongnq.service.parent.integration.cucumber.common;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.*;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static io.restassured.RestAssured.given;

@Lazy
@Configuration
@ComponentScan("org.phuongnq.service.parent")
public class CucumberIntegrationTestConfiguration {
    @LocalServerPort
    protected int port;

    @Bean
    @Scope(SCOPE_CUCUMBER_GLUE)
    protected RequestSpecification requestSpecification() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        return given();
    }

    @Bean
    @Scope(SCOPE_CUCUMBER_GLUE)
    protected SharedContext sharedContext() {
        return new SharedContext();
    }
}
