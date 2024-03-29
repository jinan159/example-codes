package com.jjikmuk.sikdorak.integration.review;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jjikmuk.sikdorak.common.controller.request.CursorPageRequest;
import com.jjikmuk.sikdorak.common.exception.InvalidPageParameterException;
import com.jjikmuk.sikdorak.integration.InitIntegrationTest;
import com.jjikmuk.sikdorak.review.command.domain.ReviewVisibility;
import com.jjikmuk.sikdorak.review.query.ReviewDao;
import com.jjikmuk.sikdorak.review.query.response.ReviewListResponse;
import com.jjikmuk.sikdorak.user.auth.api.LoginUser;
import com.jjikmuk.sikdorak.user.user.command.domain.Authority;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("통합 :  Review 피드조회(추천) 기능")
class ReviewsRecommendIntegrationTest extends InitIntegrationTest {

    @Autowired
    private ReviewDao reviewDao;

    @Test
    @DisplayName("비회원이 리뷰 피드 목록 조회 요청을 할 경우 공개범위가 public 인 리뷰들을 좋아요 순서대로 반환한다.")
    void anonymous_user_get_recommended_reviews() {
        long cursorPage = 15;
        int size = 9;
        LoginUser loginUser = new LoginUser(Authority.ANONYMOUS);
        CursorPageRequest cursorPageRequest = new CursorPageRequest(0L, cursorPage, size, true);

        ReviewListResponse recommendedReviews = reviewDao.getRecentRecommendedReviews(
            loginUser, cursorPageRequest);
        long firstReviewLikeCount = recommendedReviews.reviews().get(0).like().count();
        long secondReviewLikeCount = recommendedReviews.reviews().get(1).like().count();

        assertThat(firstReviewLikeCount).isGreaterThan(secondReviewLikeCount);
        assertThat(recommendedReviews.reviews().stream()
            .anyMatch(r -> r.reviewVisibility().equals(ReviewVisibility.PROTECTED.name()))).isFalse();
    }

    @Test
    @DisplayName("회원이 리뷰 피드 목록 조회 요청을 할 경우 공개범위가 public|protected 인 리뷰들을 반환한다.")
    void user_get_recommended_reviews() {
        long cursorPage = 0;
        int size = 5;
        LoginUser loginUser = new LoginUser(testData.kukim.getId(), Authority.USER);
        CursorPageRequest cursorPageRequest = new CursorPageRequest(0L, cursorPage, size, true);

        ReviewListResponse recommendedReviews = reviewDao.getRecentRecommendedReviews(
            loginUser, cursorPageRequest);

        assertThat(recommendedReviews.reviews().stream().anyMatch(
            r -> r.reviewVisibility().equals(ReviewVisibility.PROTECTED.name()))).isTrue();
    }

    @Test
    @DisplayName("리뷰 피드 목록 조회 요청시 존재하지 않는 페이지를 요청할 경우 빈배열을 반환한다.")
    void get_invalid_page_recommended_reviews() {
        long invalidPage = -1L;
        int size = 5;
        LoginUser loginUser = new LoginUser(Authority.ANONYMOUS);
        CursorPageRequest cursorPageRequest = new CursorPageRequest(0L, invalidPage, size, true);

        ReviewListResponse recommendedReviews = reviewDao.getRecentRecommendedReviews(
            loginUser, cursorPageRequest);

        assertThat(recommendedReviews.reviews()).isEmpty();
    }

    @Test
    @DisplayName("리뷰 피드 목록 조회 시 요청하는 id가 0이면 첫 페이지의 데이터를 반환한다.")
    void get_recommended_reviews_in_first_page() {
        long cursorPage = 0;
        int size = 10;
        LoginUser loginUser = new LoginUser(Authority.ANONYMOUS);
        CursorPageRequest cursorPageRequest = new CursorPageRequest(0L, cursorPage, size, true);

        ReviewListResponse recommendedReviews = reviewDao.getRecentRecommendedReviews(
            loginUser, cursorPageRequest);

        assertThat(recommendedReviews.reviews()).hasSize(size);
    }

    @Test
    @DisplayName("리뷰 피드 목록 조회 시 요청한 페이지부터 요청한 사이즈 개수의 데이터를 반환한다.")
    void get_recommended_reviews_with_page_and_size() {
        long cursorPage = 20;
        int size = 7;
        LoginUser loginUser = new LoginUser(Authority.ANONYMOUS);
        CursorPageRequest cursorPageRequest = new CursorPageRequest(0L, cursorPage, size, true);

        ReviewListResponse recommendedReviews = reviewDao.getRecentRecommendedReviews(
            loginUser, cursorPageRequest);

        assertThat(recommendedReviews.reviews()).hasSize(size);
        assertThat(recommendedReviews.reviews().stream().allMatch(r -> r.reviewId() < cursorPage)).isTrue();
    }

    @Test
    @DisplayName("조회 시 요청할 수 있는 최대 사이즈를 벗어날 경우 예외를 반환한다.")
    void invalid_page_size() {
        long cursorPage = 11;
        int size = Integer.MAX_VALUE;
        LoginUser loginUser = new LoginUser(Authority.ANONYMOUS);
        CursorPageRequest cursorPageRequest = new CursorPageRequest(0L, cursorPage, size, true);

        assertThatThrownBy(() -> reviewDao.getRecentRecommendedReviews(loginUser, cursorPageRequest))
            .isInstanceOf(InvalidPageParameterException.class);
    }

}
