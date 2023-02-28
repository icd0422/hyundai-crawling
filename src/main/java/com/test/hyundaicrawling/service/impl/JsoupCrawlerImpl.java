package com.test.hyundaicrawling.service.impl;

import com.test.hyundaicrawling.exception.ApplicationException;
import com.test.hyundaicrawling.service.ExternalCrawler;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class JsoupCrawlerImpl implements ExternalCrawler {

    private ExecutorService externalCrawlerExecutor;

    private static final int CRAWLER_THREAD_POOL_COUNT = 100;
    private static final int CRAWLER_MAX_TIME_OUT_MILLIS = 10000;
    private static final int CRAWLER_MAX_RETRY_COUNT = 3;

    @PostConstruct
    private void initialize() {
        externalCrawlerExecutor = Executors.newFixedThreadPool(CRAWLER_THREAD_POOL_COUNT);
    }

    @Override
    public List<String> getHTMLs(List<String> urls) {
        List<String> result = new ArrayList<>();

        CompletableFuture.allOf(urls.stream().map(url -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return getHTML(url);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return "";
                    }
                }, externalCrawlerExecutor).thenAccept(result::add))
                .toArray(CompletableFuture[]::new)).join();

        return result;
    }

    private String getHTML(String url) throws InterruptedException {
        String result = "";

        for (int i = 0; i < CRAWLER_MAX_RETRY_COUNT; i++) {
            try {
                result = Jsoup.connect(url).timeout(CRAWLER_MAX_TIME_OUT_MILLIS).get().html();
                break;
            } catch (MalformedURLException e) {
                throw new ApplicationException.FetchHtmlMalformedURLException();
            } catch (SocketTimeoutException e) {
                log.warn("JsoupCrawlerImpl getHTML time out - exceed millis : " + CRAWLER_MAX_TIME_OUT_MILLIS, e);
                Thread.sleep(500);
            } catch (Exception e) {
                log.error("JsoupCrawlerImpl getHTML fail", e);
                Thread.sleep(1000);
            }
        }

        return result;
    }
}
