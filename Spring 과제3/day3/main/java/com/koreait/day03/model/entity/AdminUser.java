package com.koreait.day03.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data                   // getter/setter 를 지원
@AllArgsConstructor     // 모든 매개변수를 지원하는 생성자를 생성
@NoArgsConstructor      // 매개변수가 없는 생성자를 생성
@Entity

// 시퀀스를 만든것을 연결시켜줌
@SequenceGenerator(
        name="seq_user",                // name 은 자바에서 사용하는 것이므로 임의로 작성할 수 있음
        sequenceName = "seq_user",      // 오라클에서 만든 시퀀스 이름을 작성해주어야함
        initialValue = 1,               // 시퀀스 만들 때 정의를 했지만 여기서도 따로 정의할 수 있음
        allocationSize = 1
)

// ** 어노테이션은 바로 아래 문장에만 적용됨.
// ** 어노테이션이 필요한 문장의 바로 위에다가 작성해주는 것.

// 메소드 체이닝을 할 수 있는 어노테이션 추가
@Builder
// 값이 들어올 것을 대기하는데,
@EntityListeners(AuditingEntityListener.class)
public class AdminUser {
    @Id
    // generator = "" 의 이름과 위에 @SequenceGenerator 의 name = "" 과 일치해야함. (내가 만든 이름을 적어야함)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    private Long id;                    // 일렬번호 (오라클에서 만든 시퀀스가 id로 들어가게 됨)
    private String userid;              // 관리자 아이디
    private String userpw;              // 비밀번호
    private String name;                // 이름
    private String status;              // 상태
    private LocalDateTime lastLoginAt;  // 마지막 접속시간
    @CreatedDate
    private LocalDateTime regDate;      // 가입일

    @CreatedBy
    private String createBy;
}
