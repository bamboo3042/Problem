package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.OrderGroup;
import com.koreait.day2.model.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderGroupRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){
        OrderGroup orderGroup = OrderGroup.builder()
                .orderType("ALL")
                .status("결제완료")
                .revAddress("서울시 서초구 양재동")
                .revName("김사과")
                .paymentType("카드")
                .totalPrice(BigDecimal.valueOf(4500000))
                .totalQuantity(2)
                .regDate(LocalDateTime.now())
                .orderAt(LocalDateTime.now())
                .userid(3L)
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
    }

    @Test
    public void read(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findByOrderGroupId("test");
        if(orderGroup != null){
            System.out.println("데이터가 존재합니다!");
        }else{
            System.out.println("데이터가 존재하지 않습니다!");
        }
    }

    @Test
    public void update(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findByOrderGroupId("test");
        orderGroup.ifPresent(selectOrderGroup -> {
            selectOrderGroup.setStatus("test");
            orderGroupRepository.save(selectOrderGroup);
        });
    }

    @Test
    public void delete(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findByOrderGroupId("test");

        orderGroup.ifPresent(selectUser -> {
            orderGroupRepository.delete(selectUser);
        });

        Optional<OrderGroup> deleteOrderGroup = orderGroupRepository.findByOrderGroupId("test");
        if(deleteOrderGroup.isPresent()){
            System.out.println("삭제실패!");
        }else{
            System.out.println("삭제성공!");
        }
    }
}
