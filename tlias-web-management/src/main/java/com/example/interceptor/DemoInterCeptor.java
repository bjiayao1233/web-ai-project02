package com.example.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class DemoInterCeptor implements HandlerInterceptor {

    // 初始化方法，在web服务器启动时调用一次 返回值为true表示放行，为false表示拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle拦截器方法运行了...");
        return true; // 返回true表示放行，false表示拦截
    }

    // 在Controller方法执行之后，ModelAndView返回之前调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle拦截器方法运行了...");
    }

    // 在Controller方法执行之后，ModelAndView返回之后调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion拦截器方法运行了...");

    }
}
