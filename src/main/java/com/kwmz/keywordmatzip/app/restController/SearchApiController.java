package com.kwmz.keywordmatzip.app.restController;

import com.kwmz.keywordmatzip.app.dto.KeywordResponseDto;
import com.kwmz.keywordmatzip.app.service.NaverSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchApiController {

    private final NaverSearchService naverSearchService;
    @GetMapping("/api/search")
    public KeywordResponseDto searchKeyword(@RequestParam("keyword") String keyword){
        if(keyword == null || keyword.isEmpty()){
            return null;
        }
        return naverSearchService.getKeywordVolume(keyword.trim());
    }
}
