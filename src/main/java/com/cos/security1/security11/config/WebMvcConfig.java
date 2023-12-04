package com.cos.security1.security11.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // mustache view resolver 재설정
        MustacheViewResolver resolver = new MustacheViewResolver();
        resolver.setCharset("UTF-8");
        resolver.setContentType("text/html; charset=UTF-8"); // html 파일을 던져줄거야!
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html"); // .html 파일을 만들어도 머스테치가 인식

        registry.viewResolver(resolver);
    }
}
