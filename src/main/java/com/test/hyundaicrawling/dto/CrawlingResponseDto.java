package com.test.hyundaicrawling.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CrawlingResponseDto extends SuccessResponse {

    @JsonProperty("Merge")
    private final String merge;
}
