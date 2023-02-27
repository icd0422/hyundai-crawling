package com.test.hyundaicrawling.controller;

import com.test.hyundaicrawling.dto.CrawlingResponseDto;
import com.test.hyundaicrawling.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @GetMapping("/api/crawling/merged-result")
    public CrawlingResponseDto crawling() {
        return crawlingService.crawling();
    }
}
