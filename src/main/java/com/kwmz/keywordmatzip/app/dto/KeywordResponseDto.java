package com.kwmz.keywordmatzip.app.dto;

import com.kwmz.keywordmatzip.app.entity.Search;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 매개변수 없는 기본 생성자를 생성하는 어노테이션
@AllArgsConstructor // 선언된 모든 필드를 순서대로 받는 생성자를 생성하는 어노테이션
public class KeywordResponseDto {
    private String keyword;
    private String pcCnt;
    private String mobileCnt;
    private int totalCnt;

    public static KeywordResponseDto from (Search e) {
        return new KeywordResponseDto(
                e.getKeyword(),
                e.getPcCnt(),
                e.getMobileCnt(),
                e.getTotlaCnt()
        );
    }
}
