package com.cos.security1.security11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        // -> config로 html 파일도 인식될 수 있게 설정함

    }

    // 로그인한 사람만 접근 가능
    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

    // 로그인 한 사람들 중에 admin만 접근 가능
    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    // 로그인 한 사람들 중에 manager만 접근 가능
    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

    // login의 경우, 스프링 시큐리티가 해당 주소를 낚아채버림
    // -> securityConfig 파일을 작성 후 작동안함
    @GetMapping("/login")
    public @ResponseBody String login(){
        return "login";
    }

    // 회원가입을 하는 사람들 (로그인 하기 전)
    @GetMapping("/join")
    public @ResponseBody String join(){
        return "join";
    }

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc(){
        return "회원가입 완료됨!";
    }


}
