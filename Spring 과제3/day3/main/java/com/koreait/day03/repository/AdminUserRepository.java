package com.koreait.day03.repository;

import com.koreait.day03.model.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// AdminUser가 entity가 되고 자료형은 롱형!
// <> 안에 DTO 와, 데이터 타입을 넣어줌
// 그것을 상속하는 인터페이스를 가져다가 사용할 수 있음
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
}
