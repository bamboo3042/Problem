package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.Item;
import com.koreait.day2.model.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = Item.builder()
                .name("엘지 냉장고")
                .status("판매중")
                .title("양문형 냉장고")
                .content("아주 시원해요")
                .price(BigDecimal.valueOf(2000000))
                .regDate(LocalDateTime.now())
                .partnerId(4L)
                .build();
        Item newItem = itemRepository.save(item);
    }

    @Test
    public void read(){
        Optional<Item> item = itemRepository.findByItemid("1111");
        if(item != null){
            System.out.println("데이터가 존재합니다!");
        }else{
            System.out.println("데이터가 존재하지 않습니다!");
        }
    }

    @Test
    public void update(){
        Optional<Item> item = itemRepository.findByItemid("1111");
        item.ifPresent(selectItem -> {
            selectItem.setName("과자1");
            selectItem.setUpdateDate(LocalDateTime.now());
            itemRepository.save(selectItem);
        });
    }

    @Test
    public void delete(){
        Optional<Item> item = itemRepository.findByItemid("1111");

        item.ifPresent(selectUser -> {
            itemRepository.delete(selectUser);
        });

        Optional<Item> deleteItem = itemRepository.findByItemid("1111");
        if(deleteItem.isPresent()){
            System.out.println("삭제실패!");
        }else{
            System.out.println("삭제성공!");
        }
    }
}
