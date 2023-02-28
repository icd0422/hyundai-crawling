package com.test.hyundaicrawling.controller;

import com.test.hyundaicrawling.exception.ApplicationException;
import com.test.hyundaicrawling.exception.ApplicationExceptionHandler;
import com.test.hyundaicrawling.service.ExternalCrawler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.cache.CacheManager;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CrawlingControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CrawlingController crawlingController;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @Autowired
    private CacheManager cacheManager;

    @MockBean
    private ExternalCrawler externalCrawler;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(crawlingController)
                .setControllerAdvice(applicationExceptionHandler)
                .alwaysDo(print())
                .build();

        cacheManager.getCache("mergedContentCache").clear();
    }

    @Test
    @DisplayName("merged-result 조회 - 성공 테스트")
    void successTest() throws Exception {
        // given
        String uri = "/api/crawling/merged-result";
        given(externalCrawler.getHTMLs(any())).willReturn(Arrays.asList("a", "b", "c"));

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Status").value(200))
                .andExpect(jsonPath("$.Merge").value("abc"));
    }

    @Test
    @DisplayName("merged-result 조회 - 실패 테스트")
    void failTest() throws Exception {
        // given
        String uri = "/api/crawling/merged-result";
        ApplicationException.FetchHtmlMalformedURLException fetchHtmlMalformedURLException = new ApplicationException.FetchHtmlMalformedURLException();
        given(externalCrawler.getHTMLs(any())).willThrow(fetchHtmlMalformedURLException);

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(fetchHtmlMalformedURLException.getErrorCode()))
                .andExpect(jsonPath("$.reason").value(fetchHtmlMalformedURLException.getReason()));
    }
}
