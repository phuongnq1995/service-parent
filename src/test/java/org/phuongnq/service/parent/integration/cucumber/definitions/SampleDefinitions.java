package org.phuongnq.service.parent.integration.cucumber.definitions;

import groovy.util.logging.Slf4j;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.phuongnq.service.parent.integration.cucumber.common.SharedContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.*;

@Slf4j
public class SampleDefinitions {

    @Autowired
    SharedContext sharedContext;

    @When("the client calls url {string}")
    public void the_client_issues_GET_version(String url) {
        var response = given(requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then();
        sharedContext.setResponse(response);
    }

    @Then("the client receives status code of {int}")
    private void receiveStatus(int status) {
        sharedContext.getResponse().assertThat().statusCode(equalTo(status));
    }

    @And("the client receives response data {columns}")
    private void receiveResponse(List<String> columns) {
        columns.forEach(column -> {
            sharedContext.getResponse().assertThat().body(hasKey(column));
        });
    }
}
