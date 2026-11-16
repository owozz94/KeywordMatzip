package com.kwmz.keywordmatzip.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeywordResponseDto {
    private String keyword;
    private String pcCnt;
    private String mobileCnt;
    private int totalCnt;
    private String compIdx; //경쟁정도(높음/중간/낮음)
}
