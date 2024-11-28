package com.metacoding.authblog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller // 정상코드만 들어옴
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/login-form")
    public String loginForm(){
        return"user/login-form";
    }


    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }
}
