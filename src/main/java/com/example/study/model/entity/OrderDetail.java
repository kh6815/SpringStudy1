package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity //order_detail 테이블에 자동연결
//@ToString(exclude = {"user" , "item"}) //두 클래스에서 연관관계가 있는 변수는 서로 참조하고 있기때문에 toString에서 StackOverflowError가 발생한다.
// 고로 exclude를 사용하여 toString을 제외시켜준다.
@ToString(exclude = {"orderGroup", "item"})
@EntityListeners(AuditingEntityListener.class)
@Builder //요즘은 Builder를 통해 생성자 생성을 쉽게 하기 위해 사용된다.
@Accessors(chain = true) //@Accessors(chain = true)를 통해서 업데이트시 에 builder와 같이 .으로 값을 바꿔줄 수 있다.
public class OrderDetail {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    // N : 1 (orderDetail입장에서는 자신은 N이고 User는 1이다. -> 유저는 여러개의 orderDetail을 가질 수 있다.)
    @ManyToOne
    private User user; //이렇게 객체 타입을 맞춰주면 알아서 하이버네이트가 매칭 시켜줌. -> user_id

    //N : 1
    @ManyToOne
    private Item item;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity; //int와의 차이점 - Integer는 클래스 형태이기 때문에 산술연산을 바로 할 수 없고 null 값이 들어갈 수 있어 db데이터처리할 때 자주 사용됨.

    private BigDecimal totalPrice;

    @CreatedDate // AuditingEntityListener를 통해 처음 생성될때 자동으로 값이 들어가짐.
    private LocalDateTime createdAt;

    @CreatedBy // AuditingEntityListener를 통해 처음 생성될때 자동으로 값이 들어가짐.
    private String createdBy;

    @LastModifiedDate //AuditingEntityListener를 통해 업데이트될때 자동으로 값이 들어가짐.
    private LocalDateTime updatedAt;

    @LastModifiedBy //AuditingEntityListener를 통해 업데이트될때 자동으로 값이 들어가짐.
    private String updatedBy;

    //OrderDetail N : 1 Item
    @ManyToOne
    private Item item;

    //OrderDetail N : 1 OrderGroup
    @ManyToOne
    private OrderGroup orderGroup;
}
