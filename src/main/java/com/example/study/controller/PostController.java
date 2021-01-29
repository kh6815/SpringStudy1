package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    //@RequestMapping(method = RequestMethod.POST, path = "/postMethod") -> 밑에것과 동일
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){

        return searchParam;
    }

    @PutMapping("/put")
    public void put(){

    }

    @PatchMapping("/post")
    public void patch(){

    }
}
