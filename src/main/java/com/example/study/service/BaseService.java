package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {

    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;
    //Entity값으로 Item이 들어오면 JpaRepository<Item, Long>이 된다. 그래서 ItemRepository를 자동주입을 받기위해 해당 클래스를 @Component해주고 @Autowired를 해준다.

}
