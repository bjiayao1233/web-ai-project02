package com.example.config;

import com.example.interceptor.DemoInterCeptor;
import com.example.interceptor.TokenInterCeptor;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private DemoInterCeptor demoInterCeptor;

    @Autowired
    private TokenInterCeptor tokenInterCeptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterCeptor)
                .addPathPatterns("/**")// 拦截所有请求
                .excludePathPatterns("/login");//不拦截登录
    }
}
