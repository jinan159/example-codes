package com.jjikmuk.sikdorak.integration.user.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.jjikmuk.sikdorak.integration.InitIntegrationTest;
import com.jjikmuk.sikdorak.user.user.query.response.UserSimpleProfileResponse;
import com.jjikmuk.sikdorak.user.user.query.UserDao;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("통합 : User 닉네임 검색,조회")
class UserSearchIntegrationTest extends InitIntegrationTest {

    @Autowired
    private UserDao userDao;

    @Test
    @DisplayName("유저의 닉네임으로 검색할 경우 유저 검색결과를 반환한다.")
    void search_users_by_full_nickname() {
        String searchNickname = testData.forky.getNickname();

        List<UserSimpleProfileResponse> result = userDao.searchUsersByNickname(searchNickname);

        assertThat(result).isNotEmpty();
        assertThat(result)
            .extracting(UserSimpleProfileResponse::nickname)
            .contains(testData.forky.getNickname());
    }

    @Test
    @DisplayName("닉네임 일부분으로 검색할 경우 유저 검색결과를 반환한다.")
    void search_users_by_part_nickname() {
        String searchNickname = testData.forky.getNickname().substring(0, 1);

        List<UserSimpleProfileResponse> result = userDao.searchUsersByNickname(searchNickname);

        assertThat(result).isNotEmpty();
        assertThat(result)
            .extracting(UserSimpleProfileResponse::nickname)
            .contains(testData.forky.getNickname());
    }

    @Test
    @DisplayName("검색하는 닉네임이 비어있을 경우 비어있는 목록을 반환한다.")
    void search_users_by_blank() {
        String searchNickname = "";

        List<UserSimpleProfileResponse> result = userDao.searchUsersByNickname(searchNickname);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("검색결과가 존재하지 않을 경우 비어있는 목록을 반환한다.")
    void search_users_by_invalid_nickname() {
        String searchNickname = "honux";

        List<UserSimpleProfileResponse> result = userDao.searchUsersByNickname(searchNickname);

        assertThat(result).isEmpty();
    }

}
