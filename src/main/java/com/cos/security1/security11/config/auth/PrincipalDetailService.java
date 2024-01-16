package com.cos.security1.security11.config.auth;

import com.cos.security1.security11.model.User;
import com.cos.security1.security11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login")로 걸어놨으므로
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailService implements UserDetailsService {
    // html에서 name을 username이라고 잘 적어줘야함
    // -> 다른 걸로 받았다면 시큐리티 설정에서 .usernameParameter()으로 이름을 바꿔줘야함

    @Autowired
    private UserRepository userRepository;

    // return된 값이 Authentication안에 들어감
    // Security session(내부 Authentication(내부 UserDetails))
    // 이 함수가 Authentication을 Session에 넣어주는 작업까지 실행해서 로그인이 완료됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null){
            return new PrincipalDetails(user);
        }
        return null;
    }
}
