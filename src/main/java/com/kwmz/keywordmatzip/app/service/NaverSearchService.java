package com.kwmz.keywordmatzip.app.service;

import com.kwmz.keywordmatzip.app.dto.KeywordResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.Objects;

@Service
public class NaverSearchService {

    @Value("${naver.api.customer-id}")
    private String customerId;
    @Value("${aver.api.access-license")
    private String accessLicense;
    @Value("${naver.api.client-secret-key")
    private String secretKey;
    @Value("${naver.api.base-url}")
    private String baseUrl;

    public KeywordResponseDto getKeywordVolume(String keyword){
        KeywordResponseDto result = new KeywordResponseDto();
        result.setKeyword(keyword);

        String timestamp = String.valueOf(System.currentTimeMillis());
        String method = "GET";
        String uri = "/keywordstool";
        try {
            String signature = generateSignature(timestamp, method, uri, secretKey);
            //네이버에서 요구하는 헤더값
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Timestamp", timestamp);
            headers.set("X-API-KEY", accessLicense);
            headers.set("X-Customer", customerId);
            headers.set("X-Signature", signature);

            HttpEntity<String> entity = new HttpEntity<>(headers); //HTTP 요청을 보낼때 실어보낼 데이터(body)와 메타데이터(Header)를 하나로 묵어주는 래퍼 객체(get 요청이므로 header만있음)
            RestTemplate restTemplate = new RestTemplate(); //HTTP통신 클라이언트 객체(복잡함 HttpURLConnection 이나 외부 라이르러리(HttpClient 등 사용X)

            String requestUrl = baseUrl + uri + "?hintKeywords=" + keyword + "&showDetail=1";
            //실제로 네이버 API서버에 네트워킹을 시도하여 요청을 날리고 응답을 받아옴)
            ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, String.class);

            //JSON 파싱
            ObjectMapper mapper = new ObjectMapper(); // JSON형태의 텍스트와 자바 객체(트리구조)를 상호 변환해주는 변환기(Parser) 객체
            JsonNode rootNote = mapper.readTree(response.getBody()); //JSON 문자열을 트리 구조 객체로 변환
            JsonNode keywordList = rootNote.path("keywordList"); //최상위 노드에서 원하는 키워드 배열 데이터 추출(키가 없더라도 NPE X, 안전하게 비어있는 노드객체 반환)

            if(keywordList.isArray() && keywordList.size() > 0) {
                JsonNode target = keywordList.get(0);

                String pcStr = target.path("monthlyPcQcCnt").asText("0");
                result.setPcCnt(pcStr);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    //네이버 API 인증 서명 생성
    private String generateSignature(String timestamp, String method, String uri, String secretKey) throws Exception{
        String message = timestamp+"."+method+"."+uri;
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"),"HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256"); //HMAC-SHA256 알고리즘 객체 생성
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8")); //암호화된 순수 바이트배열 형태의 해시결과물
        return Base64.getEncoder().encodeToString(rawHmac);
    }
}
