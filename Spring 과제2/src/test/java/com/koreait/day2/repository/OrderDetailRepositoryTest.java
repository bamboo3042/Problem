package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.OrderDetail;
import com.koreait.day2.model.entity.OrderGroup;
import com.koreait.day2.model.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = OrderDetail.builder()
                .status("결제완료")
                .quantity(1)
                .totalPrice(BigDecimal.valueOf(3000000))
                .regDate(LocalDateTime.now())
                .itemId(2L)
                .orderGroupId(2L)
                .build();
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
    }

    @Test
    public void read(){
        Optional<OrderDetail> orderGroup = orderDetailRepository.findByOrderDetaileid("test");
        if(orderGroup != null){
            System.out.println("데이터가 존재합니다!");
        }else{
            System.out.println("데이터가 존재하지 않습니다!");
        }
    }

    @Test
    public void update(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findByOrderDetaileid("1111L");
        orderDetail.ifPresent(selectOrderDetail -> {
            selectOrderDetail.setOrderGroupId(1112L);
            selectOrderDetail.setStatus("test");
            orderDetailRepository.save(selectOrderDetail);
        });
    }

    @Test
    public void delete(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findByOrderDetaileid("1111L");

        orderDetail.ifPresent(selectOrderDetail -> {
            orderDetailRepository.delete(selectOrderDetail);
        });

        Optional<OrderDetail> deleteOrderDetail = orderDetailRepository.findByOrderDetaileid("1111L");
        if(deleteOrderDetail.isPresent()){
            System.out.println("삭제실패!");
        }else{
            System.out.println("삭제성공!");
        }
    }
}
