package org.example.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ReviewTest {

    private static final AtomicLong id = new AtomicLong(0L);
    private static final AtomicLong seq = new AtomicLong(0L);

    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        insertReviewNTimes(10000);
    }

    @Transactional
    void insertReviewNTimes(int n) {
        Random storeId = new Random();

        List<Review> reviews = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            reviews.add(new Review(
                id.getAndIncrement(),
                seq.getAndIncrement(),
                storeId.nextLong(),
                ""
            ));
        }

        reviewRepository.saveAll(reviews);
    }

    @Test
    void test() {

    }
}