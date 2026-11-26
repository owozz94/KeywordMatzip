package com.kwmz.keywordmatzip.app.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_KEYWORD_MASTER")
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED) //JPA가 쓸 빈 생성자 만들어줌
public class Search {

    @Id
    @SequenceGenerator(name="seq_tb_keyword_master",
            sequenceName="SEQ_TB_KEYWORD_MASTER",
    allocationSize = 1)
    private Long searchId;
    private String keyword;
    private String pcCnt;
    private String mobileCnt;
    private int totlaCnt;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
