package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = @Index(name = "idx_seq_storeId", columnList = "seq, storeId"))
public class Review {

    @Id
    private Long id;

    private Long seq; // 테스트를 위한 컬럼(복합 인덱스)

    private Long storeId;

    private String reviewContent;
}
