package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    /*
    @Autowired
    private UserRepository userRepository;*/

    private final UserRepository userRepository;

    // 1. request data를 가져오기
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // id를 가지고 -> Repository를 통해서 getOne, getById를 통해서 데이터를 가져온다.

        Optional<User> optional = userRepository.findById(id);

        //해당데이터와 user가 오면 -> userApiResponse return해준다.

        return optional
                .map(user -> response(user)) //mapper 함수를 통해 입력값을 다른 값으로 변환하는 메서드입니다. // user를 -> Header<UserApiResponse>로 변환하여 반환
                .orElseGet(() -> Header.ERROR("데이터없음")); //최종적으로 연산을 끝낸 후에도 옵셔널 객체가 비어있다면 기본값으로 제공할 공급자 함수Supplier를 지정합니다.
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data를 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. id를 통해 -> user 데이터를 찾기
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional
                .map(user -> {
                    // 3. update
                    user.setAccount(userApiRequest.getAccount())
                            .setPassword(userApiRequest.getPassword())
                            .setStatus(userApiRequest.getStatus())
                            .setPhoneNumber(userApiRequest.getPhoneNumber())
                            .setEmail(userApiRequest.getEmail())
                            .setRegisteredAt(userApiRequest.getRegisteredAt())
                            .setUnregisteredAt(userApiRequest.getUnregisteredAt());

                    return user;
                })// 4. userApiResponse를 만들어주기
                .map(user -> {return userRepository.save(user);}) //update -> updateUser
                .map(updateUser -> { return response(updateUser); }) //userApiResponse
                .orElseGet(() -> Header.ERROR("데이터없음")); //위에 람다식중에 데이터가 없다면 실행
    }

    @Override
    public Header delete(Long id) {
        // 1. id를 통해서 -> repository에서 -> user를 찾기
        Optional<User> optional = userRepository.findById(id);

        // 2. repository를 통해서  -> delete를 해주기
        return optional
                .map(user -> {
                    userRepository.delete(user);
                    // 3. response return
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user){
        // user -> userApiResponse를 만들어서 return 해줌

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) //todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return Header.OK(userApiResponse);
    }
}
