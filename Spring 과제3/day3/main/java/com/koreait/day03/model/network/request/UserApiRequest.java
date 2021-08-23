package com.koreait.day03.model.network.request;

import com.koreait.day03.model.enumclass.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {   // 데이터를 담을 클래스 = Entity 패키지 안의 클래스(=DTO)와 동일
    private Long id;
    private String userid;
    private String userpw;
    private String hp;
    private String email;
    private LocalDateTime regDate;
    private UserStatus status;
}
