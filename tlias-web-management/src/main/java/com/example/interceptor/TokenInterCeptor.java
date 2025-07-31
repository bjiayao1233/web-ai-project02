package com.example.interceptor;

import com.example.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component

public class TokenInterCeptor  implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        /*//1.获取请求路径
        String requestURI = request.getRequestURI();// 获取请求的URI：
        //2.判断是否是登录请求         /login  放行
        if (requestURI.contains("/login")) {
            log.info("登录请求，放行...");
            return  true;
        }*/
        //3. 获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否存在，不存在则返回错误信息（401）
        if (token == null || token.isEmpty()) {
            log.info("令牌不存在，返回401错误...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置状态码为401
            return false;

        }
        //5.存在则通过，校验令牌   错误（401）
        try {
            JwtUtils.parseToken(token); // 解析令牌，如果令牌无效或过期，将抛出异常
        } catch (Exception e) {
            log.info("令牌无效或已过期，返回401错误...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //6.通过 放行
        log.info("令牌有效，放行...");
        return true;
    }
}
