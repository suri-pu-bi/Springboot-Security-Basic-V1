package com.cos.security1.security11.controller;

import com.cos.security1.security11.model.User;
import com.cos.security1.security11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // View를 리턴!
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    // 회원가입을 하는 사람들 (로그인 하기 전)
    @PostMapping("/join")
    public String join(User user){
        System.out.println(user);
        user.setRole("USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        // 회원가입 잘됨 그러나, 패스워드가 암호화가 되지 않으면 시큐리티로 로그인을 할 수 없음
        userRepository.save(user);
        return "redirect:/loginForm";
    }

}
