package com.example.study.model.entity;

import com.example.study.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //엔티티는 db의 테이블과 같다. ==table
//@Table(name = "user") //클래스의 이름과 mysql 테이블의 이름이 동일하다면 선언하지 않아도 자동 매핑됨.
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
@Builder //요즘은 Builder를 통해 생성자 생성을 쉽게 하기 위해 사용된다.
@Accessors(chain = true) //@Accessors(chain = true)를 통해서 업데이트시 에 builder와 같이 .으로 값을 바꿔줄 수 있다.
public class User {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(name = "account") //db의 column이름과 같다면 생략해도 자동 매칭됨.
    private String account;
    private String email;
    private String phoneNumber;

    private LocalDateTime createdAt;
    private String cratedBy;
    private LocalDateTime updateAt;
    private String updateBy;

    // 1 : N (User입장에서는 user가 1이고 OrderDetail은 n이다. -> 유저한명이 여러개의 주문을 할 수 있다. 하지만 주문은 여러 유저를 갖는건 아니다.)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") // mappedBy = "user"에 맵핑함. ->은 orderDetail.java에 있는 private User user;의 변수 이름을 뜻함.
    private List<OrderDetail> orderDetailList;*/



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String email;
    private String phoneNumber;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    @CreatedDate // AuditingEntityListener를 통해 처음 생성될때 자동으로 값이 들어가짐.
    private LocalDateTime createdAt;

    @CreatedBy // AuditingEntityListener를 통해 처음 생성될때 자동으로 값이 들어가짐.
    private String createdBy;

    @LastModifiedDate //AuditingEntityListener를 통해 업데이트될때 자동으로 값이 들어가짐.
    private LocalDateTime updatedAt;

    @LastModifiedBy //AuditingEntityListener를 통해 업데이트될때 자동으로 값이 들어가짐.
    private String updatedBy;

    //User 1 : N OrderGroup
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;
}
