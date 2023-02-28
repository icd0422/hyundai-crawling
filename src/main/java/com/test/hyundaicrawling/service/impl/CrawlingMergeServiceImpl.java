package com.test.hyundaicrawling.service.impl;

import com.test.hyundaicrawling.domain.model.AlphanumericData;
import com.test.hyundaicrawling.service.CrawlingMergeService;
import com.test.hyundaicrawling.service.ExternalCrawler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class CrawlingMergeServiceImpl implements CrawlingMergeService {

    private final ExternalCrawler externalCrawler;

    @Override
    public String getMergedContent(List<String> urls) {
        return new AlphanumericData(externalCrawler.getHTMLs(urls)).merged();
    }
}
