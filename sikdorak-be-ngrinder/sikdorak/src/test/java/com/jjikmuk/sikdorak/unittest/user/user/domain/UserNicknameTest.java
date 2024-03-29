package com.jjikmuk.sikdorak.unittest.user.user.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jjikmuk.sikdorak.user.user.command.domain.UserNickname;
import com.jjikmuk.sikdorak.user.user.exception.InvalidUserNicknameException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayName("단위 : User Nickname 클래스")
public class UserNicknameTest {
    @Nested
    @DisplayName("생성자")
    class Describe_constructor {

        @Nested
        @DisplayName("만약 유저의 닉네임이 정상적이지 않은 값이 들어올 경우")
        class Context_with_invalid_nickname {

            @ParameterizedTest
            @NullAndEmptySource
            @MethodSource("provideNicknameForIsNullAndEmptyAnd30char")
            @DisplayName("예외를 반환한다.")
            void throw_Exception(String nickname) {
                assertThatThrownBy(() -> new UserNickname(nickname))
                        .isInstanceOf(InvalidUserNicknameException.class);
            }

            private static Stream<Arguments> provideNicknameForIsNullAndEmptyAnd30char() {
                String nickname = "s";
                return Stream.of(
                        Arguments.of(nickname.repeat(31))
                );
            }

        }

    }
}
