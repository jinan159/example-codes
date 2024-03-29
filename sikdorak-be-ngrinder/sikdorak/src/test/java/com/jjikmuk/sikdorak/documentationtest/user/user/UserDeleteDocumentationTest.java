package com.jjikmuk.sikdorak.documentationtest.user.user;

import static com.jjikmuk.sikdorak.documentationtest.user.user.UserSnippet.USER_DELETE_RESPONSE_SNIPPET;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

import com.jjikmuk.sikdorak.common.ResponseCodeAndMessages;
import com.jjikmuk.sikdorak.documentationtest.InitDocumentationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("문서화 : User 삭제")
class UserDeleteDocumentationTest extends InitDocumentationTest {

    @Test
    @DisplayName("유저의 탈퇴 요청이 올바르면 성공 응답 코드를 반환한다.")
    void delete_user_success() {
        given(this.spec)
            .filter(document(
                DEFAULT_RESTDOC_PATH,
                USER_DELETE_RESPONSE_SNIPPET
            ))
            .header("Authorization", testData.kukimValidAuthorizationHeader)

        .when()
            .delete("/api/users")

        .then()
            .statusCode(HttpStatus.OK.value())
            .body("code", equalTo(ResponseCodeAndMessages.USER_DELETE_SUCCESS.getCode()))
            .body("message", equalTo(ResponseCodeAndMessages.USER_DELETE_SUCCESS.getMessage()));
    }

}


