package com.jjikmuk.sikdorak.documentationtest.comment;

import static com.jjikmuk.sikdorak.documentationtest.comment.CommentSnippet.COMMENT_CREATE_PATH_VARIABLE_SNIPPET;
import static com.jjikmuk.sikdorak.documentationtest.comment.CommentSnippet.COMMENT_CREATE_REQUEST_SNIPPET;
import static com.jjikmuk.sikdorak.documentationtest.comment.CommentSnippet.COMMENT_CREATE_RESPONSE_SNIPPET;
import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

import com.jjikmuk.sikdorak.comment.command.app.request.CommentCreateRequest;
import com.jjikmuk.sikdorak.documentationtest.InitDocumentationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("문서화 : Comment 생성")
class CommentCreateDocumentationTest extends InitDocumentationTest {


	@Test
	@DisplayName("댓글 생성 요청이 정상적인 경우라면 댓글 생성 후 정상 상태 코드를 반환한다")
	void create_comment_success() {
		Long reviewId = testData.kukimPublicReview.getId();

		CommentCreateRequest commentCreateRequest = new CommentCreateRequest(
			"좋은 리뷰 감사합니다"
		);

		given(this.spec)
			.filter(document(DEFAULT_RESTDOC_PATH,
				COMMENT_CREATE_PATH_VARIABLE_SNIPPET,
				COMMENT_CREATE_REQUEST_SNIPPET,
				COMMENT_CREATE_RESPONSE_SNIPPET))
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.header("Content-type", "application/json")
			.header("Authorization", testData.kukimValidAuthorizationHeader)
			.body(commentCreateRequest)

		.when()
			.post("/api/reviews/{reviewId}/comments", reviewId)

		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
}
