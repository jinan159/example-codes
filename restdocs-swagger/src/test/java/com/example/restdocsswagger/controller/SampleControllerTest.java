package com.example.restdocsswagger.controller;

import net.minidev.json.JSONObject;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

class SampleControllerTest extends BaseControllerTest {

    private static final Snippet REQUEST_FIELDS = requestFields(
            fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
            fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
    );

    private static final Snippet RESPONSE_FIELDS = responseFields(
            fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
            fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
    );

    @Test
    void users_success_test() {
        String expectedName = "jay";
        int expectedAge = 27;

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", expectedName);
        requestBody.put("age", expectedAge);

        given(this.spec)
            .filter(document(DEFAULT_RESTDOC_PATH, REQUEST_FIELDS, RESPONSE_FIELDS))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", "application/json")
            .body(requestBody)
            .log().all()

        .when()
            .post("/users")


        .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", Matchers.equalTo(expectedName))
            .body("age", Matchers.equalTo(expectedAge));
    }
}
