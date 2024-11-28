package com.metacoding.authblog._core.config;

import com.metacoding.authblog._core.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

//@Configuration
public class FilterConfig {

    @Bean // 리턴값이 IOXC에 등록됨
    public FilterRegistrationBean addAuthFilter(){
        FilterRegistrationBean rg = new FilterRegistrationBean(); // 원래의 필터 web.xml로 톰캣에 등록하지만 이렇게 하면 spring에 등록한 필터
        rg.setFilter(new AuthenticationFilter());
        rg.addUrlPatterns("/board/*");
        rg.addUrlPatterns("/user/*");
        rg.setOrder(1);
        return rg;
    }
}
