package com.kwmz.keywordmatzip.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
    //RequestParam : 클라이언트 파라미터값을 자바 메서드로 받음
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        System.out.println("키워드:::"+keyword);
        model.addAttribute("test","테스트 결과입니다.");
        return "index";
    }
}
