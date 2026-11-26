package com.kwmz.keywordmatzip.app.service;


import com.kwmz.keywordmatzip.app.dto.KeywordResponseDto;

import java.util.List;

public interface SearchService {

    List<KeywordResponseDto> findOne(String keyword);
}
