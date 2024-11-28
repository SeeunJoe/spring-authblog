package com.metacoding.authblog._core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 비밀번호를 해시코드로 바꿔주고 비교해준다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception//->ioc에 http가 new돼서 뜬다)//
    {
        http.csrf(c->c.disable());

        //인증 주소 설정
        http.authorizeHttpRequests(r ->
                r.requestMatchers( "/s/**" ).authenticated().anyRequest().permitAll())
                .formLogin(f->
                        f.loginPage("/login-form")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/"));

        return http.build();
    }

}
