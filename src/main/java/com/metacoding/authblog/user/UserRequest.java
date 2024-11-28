package com.metacoding.authblog.user;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRequest {

    @Data
    public static class LoginDTO{
        private String username;
        private String password;
    }

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        public User toEntity(PasswordEncoder passwordEncoder) {
            String encodedPassword = passwordEncoder.encode(password);
            User user = new User(null,username,encodedPassword,email,null);
            System.out.println("password : "+password);
            System.out.println("encodedPassword : "+encodedPassword);
            return user;
        }

    }


}
