package com.test.hyundaicrawling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public abstract class SuccessResponse {

    @JsonProperty("Status")
    private int status = 200;
}
