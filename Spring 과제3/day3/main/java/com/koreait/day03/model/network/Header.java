package com.koreait.day03.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 서버가 사용자에게 보낼 양식 클래스
// user, partner 등 다른 클래스들이 유동적으로 들어와서 처리할 수 있도록 제네릭타입을 T 로 설정
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {
    private LocalDateTime transactionTime;  // api 통신시간
    private String resultCode;              // api 응답코드
    private String description;             // api 설명
    private T data;

    // Ok (insert, update, delete 가 정상작동되면 확인할 수 있는 메소드)
    public  static <T> Header<T> OK() {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // Data (select 할 때 보여질 메소드)
    public static <T> Header<T> OK(T data) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // Error (에러났을 때 보여질 메소드)
    public static <T> Header<T> ERROR(String description) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description("ERROR")
                .build();
    }
}
