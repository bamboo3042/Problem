package com.koreait.day03.model.entity;

import com.koreait.day03.model.enumclass.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_item",
        sequenceName = "seq_item",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item")
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ItemStatus status;
    private String title;
    private String content;
    private BigDecimal price;
    @CreatedDate
    private LocalDateTime regDate;          // 등록한 날짜
    @CreatedBy
    private String createBy;
    @LastModifiedDate                       // 등록한 사람
    private LocalDateTime updateDate;       // 수정한 날짜
    @LastModifiedBy
    private String updateBy;                // 수정한 사람
//    private Long partnerId;               // partner 와 연결

    @ManyToOne
    private Partner partner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
