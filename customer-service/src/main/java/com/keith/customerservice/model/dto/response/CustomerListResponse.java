package com.keith.customerservice.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        @JsonProperty("record_count")
        private long recordCount;

        @JsonProperty("current_page")
        private int currentPage;

        @JsonProperty("number_of_pages")
        private int numberOfPages;

        @JsonProperty("limit")
        private int limit;
    }
}
