package com.example.study.model.network.response;

import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderInfoApiResponse { //사용자의 주문 정보를 조회하는 Api Response

    private UserApiResponse userApiResponse;

    //private List<OrderGroupApiResponse> orderGroupApiResponsesList;

    //private List<ItemApiResponse> itemApiResponseList;

}
