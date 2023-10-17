package org.phuongnq.service.parent.integration.cucumber.common;

import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SharedContext {
    ValidatableResponse response;
}
