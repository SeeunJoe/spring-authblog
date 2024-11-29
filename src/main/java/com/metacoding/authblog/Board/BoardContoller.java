package com.metacoding.authblog.Board;

import com.metacoding.authblog.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BoardContoller {

    private final HttpSession session;

    @GetMapping("/")
    public String index() {

        return "index";
    }

    // 인증이 필요한 페이지 -> 로그인만 되면 누구나 글 쓸 수 있음
    @GetMapping("/s/board/save-form")
    public String saveForm(@AuthenticationPrincipal User user){
        System.out.println("로그인 : "+user.getUsername());
        return "board/save-form";
    }
}
