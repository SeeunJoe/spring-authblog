package com.metacoding.authblog._core.config;

import com.metacoding.authblog.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

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
                                //.defaultSuccessUrl("/")
                                .successHandler((request, response, authentication) -> {
                                    User user = (User)authentication.getPrincipal();
                                    HttpSession session = request.getSession();
                                    session.setAttribute("sessionUser", user);
                                    // 1
                                    response.sendRedirect("/");

/*    2                                response.setHeader("Location", "/");
                                    response.setStatus(302);*/

/*    3                                BufferedWriter bw = null;
                                    bw.write("dfsd\n");
                                    bw.flush();*/

/*    4                                PrintWriter pw = response.getWriter();
                                    pw.println("<script> location.href='/';</script>");*/
                                })
                );

        return http.build();
    }

}
