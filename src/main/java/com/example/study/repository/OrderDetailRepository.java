package com.example.study.repository;

import com.example.study.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Repository를 통해 자동으로 sql쿼리문을 만들어주는 CRUD를 사용할 수 있다.
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
