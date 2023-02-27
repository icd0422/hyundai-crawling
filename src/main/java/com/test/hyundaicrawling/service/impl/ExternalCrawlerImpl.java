package com.test.hyundaicrawling.service.impl;

import com.test.hyundaicrawling.exception.ApplicationException;
import com.test.hyundaicrawling.service.ExternalCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ExternalCrawlerImpl implements ExternalCrawler {

    private ExecutorService externalCrawlerExecutor;

    private static final int CRAWLER_THREAD_POOL_COUNT = 100;

    private static final int CRAWLER_MAX_TIME_OUT_MILLIS = 10000;

    @PostConstruct
    private void initialize() {
        externalCrawlerExecutor = Executors.newFixedThreadPool(CRAWLER_THREAD_POOL_COUNT);
    }

    @Override
    public List<String> getHTMLs(List<String> urls) {
        List<String> result = new ArrayList<>();

        CompletableFuture.allOf(urls.stream().map(url -> CompletableFuture.supplyAsync(() -> getHTML(url), externalCrawlerExecutor).thenAccept(result::add))
                .toArray(CompletableFuture[]::new)).join();

        return result;
    }

    private String getHTML(String url) {
        try {
            Document doc = Jsoup.connect(url).timeout(CRAWLER_MAX_TIME_OUT_MILLIS).get();
            return doc.html();
        } catch (SocketTimeoutException e) {
            throw new ApplicationException.FetchHtmlTimeoutException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
