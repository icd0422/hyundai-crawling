# Hyundai Crawling

## Intro

아래 3개의 사이트를 크롤링하여
- https://shop.hyundai.com
- https://www.kia.com
- https://www.genesis.com

merge 후

- 영문과 숫자만 parsing
- 정렬 및 중복된 문자 제거
- 대문자, 소문자, 숫자 교차 출력

위 3개의 조건을 적용 후 결과를 내려준다.

## API Spec 
http://localhost:8080

###request
Get Method

    /api/crawling/merged-result

###response
Json

    {
       "Status": {int},
       "Merge": {string}
    }

## Library
- jsoup : 1.13.1
- caffeine cache : 3.1.1
- lombok
