package com.test.hyundaicrawling.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CrawlingResponseDto {

    @JsonProperty("Status")
    private final int status;

    @JsonProperty("Merge")
    private final String merge;
}
