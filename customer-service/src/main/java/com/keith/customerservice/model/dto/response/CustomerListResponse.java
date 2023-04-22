package com.keith.customerservice.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerListResponse {
    private List<CustomerResponse> data;
    private MetaData meta;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MetaData {
        private long recordCount;
        private int currentPage;
        private int numberOfPages;
        private int limit;
    }
}
