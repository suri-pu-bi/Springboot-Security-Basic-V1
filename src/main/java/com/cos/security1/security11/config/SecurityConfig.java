package com.cos.security1.security11.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 SecurityConfig가 스프링 필터체인에 등록이 됨
public class SecurityConfig {

    // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(CsrfConfigurer::disable);
        http.authorizeHttpRequests(authorize -> authorize
                // "/user/~" 이 주소로 들어오면 인증이 필요함 -> 인증만 되면 들어갈 수 있는 주소!
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
                .loginPage("/loginForm")
                // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인 진행
                // -> 컨트롤러에 /login을 만들지 않아도 됨
                .loginProcessingUrl("/login")
                // 로그인이 완료되면 메인페이지로 이동(default), 특정페이지(/user)를 요청해서 로그인을 한 경우에는 특정페이지로 가도록 함
                .defaultSuccessUrl("/")
        );

        return http.build();


    }
}
