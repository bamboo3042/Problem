package com.koreait.day03.repository;

import com.koreait.day03.model.entity.AdminUserr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserrRepository extends JpaRepository<AdminUserr, Long> {
}
