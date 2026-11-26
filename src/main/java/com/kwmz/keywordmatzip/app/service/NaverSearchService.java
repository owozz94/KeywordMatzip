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

public interface NaverSearchService {
    // 키워드 조회 요청을 수행하고 결과를 반환
    KeywordResponseDto getKeywordVolume(String keyword);



}
