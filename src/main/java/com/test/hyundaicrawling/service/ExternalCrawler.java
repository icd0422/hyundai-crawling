package com.test.hyundaicrawling.service;

import java.util.List;

public interface ExternalCrawler {

    List<String> getHTMLs(List<String> urls);
}
