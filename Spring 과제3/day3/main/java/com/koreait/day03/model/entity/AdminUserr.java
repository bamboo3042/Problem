package com.koreait.day03.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data               // getter & setter 지원
@AllArgsConstructor // 모든 매개변수를 지원하는 생성자를 생성
@NoArgsConstructor  // 매개변수가 없는 생성자를 생성
@Entity

// 시퀀스 연결
@SequenceGenerator(
        name = "seq_userr",             // nam은 자바에서 사용하기 때문에 만들어 놓은 시퀀스명을 따라할 필요없이 새로 작성해도 됨
        sequenceName = "seq_userr",     // 여기다 만들어 놓은 시퀀스명을 입력해주어야 함
        initialValue = 1,               // 시퀀스에서 정의한 값을 여기서 따로 정의할 수도 있음.
        allocationSize = 1
)

// 메소드 체이닝을 할 수 있는 어노테이션 추가
@Builder
public class AdminUserr {
    @Id
    // 시퀀스를 사용하기 위해 어노테이션 추가 (어노테이션은 사용하고자하는 코드 바로 위에다 호출해야 적용됨)
    // @GeneratedValue 의 generator = "" 안에 들어가는 값을 @SequenceGenerator 의 name = "" 에 똑같이 입력해야함
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_userr")
    private Long serial;               // 일렬번호 (SQL 디벨로퍼에서 만든 시퀀스를 serial에 적용)
    private String userId;             // 관리자 아이디
    private String userPw;             // 관리자 비밀번호
    private String userName;           // 관리자 이름
    private String connStatus;         // 접속 상태
    private LocalDateTime lastConn;    // 마지막 접속 시간
    private LocalDateTime regiDate;    // 등록일
}