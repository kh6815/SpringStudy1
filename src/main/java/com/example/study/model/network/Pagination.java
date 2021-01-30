package com.example.study.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Pagination {

    private Integer totalPages; //총 몇개의 페이지가 있는지
    private Long totalElements; //총 몇개의 엘리먼트를 가지고 있는지 (총 유저 수가 몇명인지?)
    private Integer currentPage; //현재 페이지는 몇번째인지?
    private Integer currentElements; //총 몇개의 엘리먼트가 내려갔는지?
}
