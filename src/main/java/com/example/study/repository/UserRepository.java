package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
/*
    //select * from user where account = ? <- test03, test04등
    Optional<User> findByAccount(String account);

    //select * from user where email = ?
    Optional<User> findByEmail(String email);

    //select * from user where account = ? and email = ?
    Optional<User> findByAccountAndEmail(String account, String email);*/

    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber); // 한개의 전화번호로 여러회원가입을 할 수 있으므로 제일 최근꺼를 가져온다. -> findFirstBy

}
