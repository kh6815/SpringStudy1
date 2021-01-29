package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j //실제 현업에서는 System.out.println();으로 데이터를 보는것이 아니라 로깅시스템을 통해서 로그를 남기기 때문에
       //lombok에 있는 Simple Logging 파사드? 4 java인 @Slf4j를 사용하여 로그를 남긴다.
@RequiredArgsConstructor //lombok사용
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    /*
    @Autowired
    private UserApiLogicService userApiLogicService;*/

    private final UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("") // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        //log.info("{}, {}", request, "ABC"); //request의 변수값이 {}에 매칭된다. -> 첫번째 {} : request의 toString, 두번째 {} : "ABC"
        log.info("{}", request);
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("read : {}" , id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(@PathVariable Long id) {
        log.info("delect : {}", id);
        return userApiLogicService.delete(id);
    }
}
