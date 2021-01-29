package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.enumclass.ItemStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        /*
        Item item = new Item();

        item.setName("장난감");
        item.setPrice(1000);
        item.setContent("아이들이 좋아하는 장난감");*/

        Item item = new Item();
        item.setStatus(ItemStatus.UNREGISTERED);
        item.setName("삼성컴퓨터");
        item.setTitle("삼성 컴퓨터 A100");
        item.setContent("2019년형 노트북입니다.");
        item.setPrice(BigDecimal.valueOf(90000));
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01"); //아이템은 파트너사에서 넣기 때문에
        //item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);

        System.out.println("newItem = " + newItem);

        Assertions.assertNotNull(newItem);
    }

    @Test
    public void read(){
        Optional<Item> newItem = itemRepository.findById(1L);

        Assertions.assertTrue(newItem.isPresent());

        newItem.ifPresent(selectItem -> { //ifPresent는 아이템이 있으면 뒤에 람다식을 동작시킨다.
            System.out.println("selectItem = " + selectItem);
            Assertions.assertNotNull(selectItem);
        });

    }
}
