package com.metacoding.authblog._core.filter;

import com.metacoding.authblog.user.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        // localhost:8080/board/hello
        String path = req.getRequestURI();

  //      Boolean isAuth = path.startsWith("/board");

    //    if (isAuth) {
            User sessionUser = (User) session.getAttribute("sessionuser");
            if(sessionUser == null) {
                resp.sendRedirect("/login-form");
            }else {
                chain.doFilter(request, response);
            }
 //        }
    }
}
