package com.cos.security1.security11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // View를 리턴!
public class IndexController {

    // localhost:8080/
    // localhost:8080
    @GetMapping({"", "/"})
    public String index() {
        // 머스테치라는 템플릿 엔진 사용
        // 기본 폴더 : src/main/resources/
        // view resolver 설정 : templates (prefix), .mustache (suffix) -> application.yml
        // 머스테치를 사용하겠다고 하면 자동으로 잡아줌 -> pom 설정 생략 가능
        return "index";
        // 원래 : src/main/resources/templates/index.mustache
        // -> config로 html 파일로 인식될 수 있게 설정함

    }
}
