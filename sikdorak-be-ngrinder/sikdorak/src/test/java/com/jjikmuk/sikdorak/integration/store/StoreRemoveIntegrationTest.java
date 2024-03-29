package com.jjikmuk.sikdorak.integration.store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jjikmuk.sikdorak.integration.InitIntegrationTest;
import com.jjikmuk.sikdorak.store.command.domain.Store;
import com.jjikmuk.sikdorak.store.exception.NotFoundStoreException;
import com.jjikmuk.sikdorak.store.command.domain.StoreRepository;
import com.jjikmuk.sikdorak.store.command.app.StoreService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("통합 : Store 삭제 기능")
public class StoreRemoveIntegrationTest extends InitIntegrationTest {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    @Nested
    @DisplayName("가게를 삭제할 때")
    class ModifyStoreTest {

        @Test
        @DisplayName("존재하는 가게에 대한 삭제 요청이라면 가게를 삭제할 수 있다.")
        void remove_store_success() {
            // given
            Long deleteTargetStoreId = testData.store.getId();

            // when
            storeService.removeStore(deleteTargetStoreId);

            // then
            Optional<Store> findResult = storeRepository.findById(deleteTargetStoreId);
            assertThat(findResult).isEmpty();
        }

        @Test
        @DisplayName("존재하지 않는 가게에 대한 삭제 요청이라면 예외를 발생시킨다.")
        void create_store_failed() {
            long notSavedStoreId = Long.MIN_VALUE;

            // then
            assertThatThrownBy(() -> storeService.removeStore(notSavedStoreId))
                .isInstanceOf(NotFoundStoreException.class);
        }
    }
}
