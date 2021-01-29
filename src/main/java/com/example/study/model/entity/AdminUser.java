package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder //요즘은 Builder를 통해 생성자 생성을 쉽게 하기 위해 사용된다.
@Accessors(chain = true) //@Accessors(chain = true)를 통해서 업데이트시 에 builder와 같이 .으로 값을 바꿔줄 수 있다.
public class AdminUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String status;

    private String role;

    private LocalDateTime lastLoginAt;

    private LocalDateTime passwordUpdatedAt;

    private int loginFailCount;

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
}
