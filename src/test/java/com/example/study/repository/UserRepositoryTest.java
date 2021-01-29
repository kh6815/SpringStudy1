package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired //DI
    private UserRepository userRepository;

    @Test
    //@DisplayName("create")
    public void create(){
        /*
        User user = new User();
        //user.setId();  //db에서 ID는 자동 주입을 해놨기때문에 우리가 값을 넣을 필요가 없다.
        user.setAccount("TestUser01");
        user.setEmail("TestUser01@gmail.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCratedBy("admin");

        User newUser = userRepository.save(user);

        System.out.println("newUser : " + newUser);
        */


        String account = "Test03";
        String password = "Test03";
        UserStatus status = UserStatus.REGISTERED;
        String email = "Test@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        //user.setCreatedAt(createdAt);
        //user.setCreatedBy(createdBy);

        //@Builder - 요즘은 생성자를 builder를 통해서 사용한다. 이렇게 생성하면 내가 원하는 값만 넣고 생성자를 생성할 수 있고 수정이 용이 하다.
        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        //Optional<User> user = userRepository.findById(1L);

        /*
        user.ifPresent(selectUser ->{ //Optional에 값이 있을때만 실행하겠다.- ifPresent
            System.out.println("selectUser = " + selectUser);
            System.out.println("email:" +selectUser.getEmail());
        });*/


        /*
        Optional<User> user = userRepository.findByAccount("TestUser01");

        //OneToMany와 ManyToOne을 하고 나서
        user.ifPresent(selectUserOrder -> {
            selectUserOrder.getOrderDetailList().stream().forEach(detail -> { //stream을 통해 forEach로 list의 목록을 log에 남김.
                Item item = detail.getItem();
                System.out.println(item);
            });
        });*/

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        //@Accessors(chain = true)를 통해 좀더 쉽게 값이 수정(업데이트) 할 수 있다.
        user
                .setEmail("")
                .setPhoneNumber("");
                //.setStatus("");
        //@Accessors(chain = true)를 통해서 유저를 생성할 때 3개의 값만 가지는 유저를 생성할 수도 있다.
        User u = new User().setAccount("").setEmail("").setPassword("");

        user.getOrderGroupList().stream().forEach(orderGroup -> {

            System.out.println("--------------------장바구니 주문묶음-------------------");
            System.out.println("수령인 : " + orderGroup.getRevName());
            System.out.println("수령지 : " + orderGroup.getRevAddress());
            System.out.println("총 금액 : " + orderGroup.getTotalPrice());
            System.out.println("총 수량 : " + orderGroup.getTotalQuantity());

            System.out.println("--------------------주문 상세-------------------");
            orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태 : " + orderDetail.getStatus());
                System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());
            });

        });
        Assertions.assertNotNull(user);
    }

    @Test
    @Transactional
    public void update(){
        /*
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{ //Optional에 값이 있을때만 실행하겠다.- ifPresent
            selectUser.setAccount("pppp");
            selectUser.setUpdateAt(LocalDateTime.now());
            selectUser.setUpdateBy("update method()");

            userRepository.save(selectUser);
        });*/
    }

    @Test
    @Transactional //실질적으로 데이터베이스에 값이 들어가진 않음.
    public void delete(){
        Optional<User> user = userRepository.findById(1L);

        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);

        Assertions.assertFalse(deleteUser.isPresent());

        /*
        if(deleteUser.isPresent()){
            System.out.println("데이터 존재 : " + deleteUser.get());
        }else{
            System.out.println("데이터 삭제 데이터 없음");
        }*/
    }
}
