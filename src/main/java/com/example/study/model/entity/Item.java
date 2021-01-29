package com.example.study.model.entity;

import com.example.study.model.enumclass.ItemStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderDetailList", "partner"})
@EntityListeners(AuditingEntityListener.class)
@Builder //요즘은 Builder를 통해 생성자 생성을 쉽게 하기 위해 사용된다.
@Accessors(chain = true) //@Accessors(chain = true)를 통해서 업데이트시 에 builder와 같이 .으로 값을 바꿔줄 수 있다.
public class Item {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String content;

    //1 : N
    // FetchType.LAZY = 지연로딩 ->은 해당하는 쿼리문만 실행해서 빠르게 동작할 수 있기 때문에 N개로 이루어진 관계일 때는 이걸 사용해야한다.
    // FetchType.EAGER = 즉시로딩 ->은 관계가 있는 모든 것이 join되어 동작하기 때문에 1:1관계가 아니면 사용하지 않는 것을 추천
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemStatus status; //등록 /해지 /검수중(등록대기중)

    private String name;

    private String title;

    private String content;

    private BigDecimal price;

    private String brandName;

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

    //Item N : 1 Partner
    @ManyToOne
    private Partner partner;

    //Item 1 : N orderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

}
