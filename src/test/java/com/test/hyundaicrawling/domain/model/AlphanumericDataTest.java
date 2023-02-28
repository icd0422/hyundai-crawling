package com.test.hyundaicrawling.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.BDDAssertions.then;

class AlphanumericDataTest {

    @DisplayName("AlphanumericData - 영문,숫자 파싱 테스트")
    @ParameterizedTest
    @CsvSource({
            "@A, B~, `C@, ABC",
            "@a, b~, `c@, abc",
            "@1, 2~, `3@, 123"
    })
    void filterAlphanumericTest(String html1, String html2, String html3, String expectedResult) {
        //given
        AlphanumericData alphanumericData = new AlphanumericData(Arrays.asList(html1, html2, html3));

        //when
        String mergedContent = alphanumericData.merged();

        //then
        then(mergedContent).isEqualTo(expectedResult);
    }

    @DisplayName("AlphanumericData - 중복 제거 테스트")
    @ParameterizedTest
    @CsvSource({
            "AA, BA, CC, ABC",
            "aa, ba, cc, abc",
            "11, 21, 33, 123"
    })
    void distinctTest(String html1, String html2, String html3, String expectedResult) {
        //given
        AlphanumericData alphanumericData = new AlphanumericData(Arrays.asList(html1, html2, html3));

        //when
        String mergedContent = alphanumericData.merged();

        //then
        then(mergedContent).isEqualTo(expectedResult);
    }

    @DisplayName("AlphanumericData - 오름차순 정렬 테스트")
    @ParameterizedTest
    @CsvSource({
            "B, A, C, ABC",
            "b, a, c, abc",
            "3, 1, 2, 123"
    })
    void sortAscTest(String html1, String html2, String html3, String expectedResult) {
        //given
        AlphanumericData alphanumericData = new AlphanumericData(Arrays.asList(html1, html2, html3));

        //when
        String mergedContent = alphanumericData.merged();

        //then
        then(mergedContent).isEqualTo(expectedResult);
    }

    @DisplayName("AlphanumericData - 교차 테스트")
    @ParameterizedTest
    @CsvSource({
            "A, 1, b, A1b",
            "B, 2, 3, B23",
            "AB, 1, 2Ca, Aa1B2C"
    })
    void crossTest(String html1, String html2, String html3, String expectedResult) {
        //given
        AlphanumericData alphanumericData = new AlphanumericData(Arrays.asList(html1, html2, html3));

        //when
        String mergedContent = alphanumericData.merged();

        //then
        then(mergedContent).isEqualTo(expectedResult);
    }

    @DisplayName("AlphanumericData - 통합([영문, 숫자] 파싱, 중복 제거, 오름차순 정렬, 교차) 테스트")
    @ParameterizedTest
    @CsvSource({
            "AAAb12@, 1dfffe, ber, A1b2defr",
            "Bdd, 2@`~, ddaqA3, Aa2B3dq",
            "AzBs!~s, 112331```s, 2ddqqQCa`410, Aa0B1C2d3Qq4sz",
            "html12, 4divABC, DefgtaBIelmg1, Aa1B2C4DdefghIilmtv"
    })
    void entireTest(String html1, String html2, String html3, String expectedResult) {
        //given
        AlphanumericData alphanumericData = new AlphanumericData(Arrays.asList(html1, html2, html3));

        //when
        String mergedContent = alphanumericData.merged();

        //then
        then(mergedContent).isEqualTo(expectedResult);
    }
}
