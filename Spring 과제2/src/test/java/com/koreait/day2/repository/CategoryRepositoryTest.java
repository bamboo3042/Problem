package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.Category;
import com.koreait.day2.model.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    // 자동차2, 컴퓨터2, 가전2
    @Test
    public void create(){
        Category category = Category.builder()
                .type("가전")
                .title("엘지 냉장고")
                .regDate(LocalDateTime.now())
                .build();

        Category newCategory = categoryRepository.save(category);
    }

    @Test
    public void read(){
        Optional<Category> category = categoryRepository.findByCategorytitle("생선");
        if(category != null){
            System.out.println("데이터가 존재합니다!");
        }else{
            System.out.println("데이터가 존재하지 않습니다!");
        }
    }

    @Test
    public void update(){
        Optional<Category> category = categoryRepository.findByCategoryid("생선");
        category.ifPresent(selectCategory -> {
            selectCategory.setType("1111");
            selectCategory.setTitle("과일");
            categoryRepository.save(selectCategory);
        });
    }

    @Test
    public void delete(){
        Optional<Category> category = categoryRepository.findByCategorytitle("생선");

        category.ifPresent(selectCategory -> {
            categoryRepository.delete(selectCategory);
        });

        Optional<Category> deleteCategory = categoryRepository.findByCategorytitle("생선");
        if(deleteCategory.isPresent()){
            System.out.println("삭제실패!");
        }else{
            System.out.println("삭제성공!");
        }
    }
}
