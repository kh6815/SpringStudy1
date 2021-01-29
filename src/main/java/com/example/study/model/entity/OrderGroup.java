package com.example.study.model.entity;

import com.example.study.model.enumclass.OrderType;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"user", "orderDetailList"})
@EntityListeners(AuditingEntityListener.class)
@Builder //요즘은 Builder를 통해 생성자 생성을 쉽게 하기 위해 사용된다.
@Accessors(chain = true) //@Accessors(chain = true)를 통해서 업데이트시 에 builder와 같이 .으로 값을 바꿔줄 수 있다.
public class OrderGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @Enumerated(EnumType.STRING)
    private OrderType orderType; // 주문의 형태 - 일괄 / 개별

    private String revAddress;

    private String revName;

    private String paymentType; // 카드 / 현금

    private BigDecimal totalPrice;

    private Integer totalQuantity; //전체 수량

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    @CreatedDate // AuditingEntityListener를 통해 처음 생성될때 자동으로 값이 들어가짐.
    private LocalDateTime createdAt;

    @CreatedBy // AuditingEntityListener를 통해 처음 생성될때 자동으로 값이 들어가짐.
    private String createdBy;

    @LastModifiedDate //AuditingEntityListener를 통해 업데이트될때 자동으로 값이 들어가짐.
    private LocalDateTime updatedAt;

    @LastModifiedBy //AuditingEntityListener를 통해 업데이트될때 자동으로 값이 들어가짐.
    private String updatedBy;

    //OrderGroup N : 1 User
    @ManyToOne
    private User user;

    //OrderGroup 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}
