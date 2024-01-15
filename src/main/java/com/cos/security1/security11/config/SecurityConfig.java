package com.cos.security1.security11.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 SecurityConfig가 스프링 필터체인에 등록이 됨
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(CsrfConfigurer::disable);
        http.authorizeHttpRequests(authorize -> authorize
                // "/user/~" 이 주소로 들어오면 인증이 필요함
                .requestMatchers("/user/**").authenticated()
                // "/manager/~" 이 주소로 들어가기 위해서는 Admin과 Manager 권한이 있는 사람만 들어올 수 있음
                .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                // "/admin/**" 이 주소로 들어가기 위해서는 Admin 권한이 있는 사람만 들어올 수 있음
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                // 설정한 주소가 아니면 누구나 들어갈 수 있음
                .anyRequest().permitAll()

        );

        // /user, /admin, /manager를 입력해도 로그인 페이지로 넘어감
        http.formLogin(f->f
                .loginPage("/login"));

        return http.build();


    }
}
