package com.example.filter;

import com.example.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")// 拦截所有请求
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求路径
        String requestURI = request.getRequestURI();// 获取请求的URI：
        //2.判断是否是登录请求         /login  放行
        if (requestURI.contains("/login")) {
            log.info("登录请求，放行...");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //3. 获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否存在，不存在则返回错误信息（401）
        if (token == null || token.isEmpty()) {
            log.info("令牌不存在，返回401错误...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置状态码为401
            return;

        }
        //5.存在则通过，校验令牌   错误（401）
        try {
            JwtUtils.parseToken(token); // 解析令牌，如果令牌无效或过期，将抛出异常
        } catch (Exception e) {
            log.info("令牌无效或已过期，返回401错误...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //6.通过 放行
        log.info("令牌有效，放行...");
        filterChain.doFilter(request,response);


    }
}
