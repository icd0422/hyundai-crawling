package com.test.hyundaicrawling.service.impl;

import com.test.hyundaicrawling.dto.CrawlingResponseDto;
import com.test.hyundaicrawling.service.CrawlingService;
import com.test.hyundaicrawling.service.ExternalCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CrawlingServiceImpl implements CrawlingService {

    private final ExternalCrawler externalCrawler;

    private static final String HYUNDAI_URL = "https://shop.hyundai.com";
    private static final String KIA_URL = "https://www.kia.com";
    private static final String GENESIS_URL = "https://www.genesis.com";

    @Override
    public CrawlingResponseDto crawling() {
        externalCrawler.getHTMLs(Arrays.asList(HYUNDAI_URL, KIA_URL, GENESIS_URL));

        return null;
    }
}
