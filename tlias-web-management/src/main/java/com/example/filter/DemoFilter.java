package com.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")// 拦截所有请求
public class DemoFilter implements Filter {
    // 初始化，在web服务器启动时调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("D初始化方法运行了...");
    }

    //拦截到请求之后，执行此方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter过滤方法运行了...  放行前");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        log.info("doFilter过滤方法运行了...  放行后");
    }

    @Override
    public void destroy() {
        log.info("destroy销毁方法运行了...");
    }
}
