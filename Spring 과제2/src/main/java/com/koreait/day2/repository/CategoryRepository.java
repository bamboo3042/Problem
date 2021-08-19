package com.koreait.day2.repository;

import com.koreait.day2.model.entity.Category;
import com.koreait.day2.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryid(String categoryId);

    Optional<Category> findByCategorytitle(String categoryTitle);
}
