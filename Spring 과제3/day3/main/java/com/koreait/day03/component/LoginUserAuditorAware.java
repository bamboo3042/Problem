package com.koreait.day03.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // optional로 값을 리턴시키는데, 값이 기본값이 세팅되면
        // 최고관리자라는 기능이 무조건 들어간다.
        return Optional.of("최고관리자");
    }
}
