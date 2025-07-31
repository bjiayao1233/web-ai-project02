package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {

    @Test
    public void testGenerateJwt() {
        Map<String, Object> datamap = new HashMap<>();
        datamap.put("id", 1);
        datamap.put("username", "admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "anlid2s=") // 设置签名算法
                .addClaims(datamap)   // 添加数据
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testPaeseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc1Mzg2ODkyMn0.yHhVmmt7bLOY_may-S0tkte8oE3kA3FnlTJe0LwzTmE";
        Claims claims = Jwts.parser()
                .setSigningKey("anlid2s=")// 设置签名密钥
                .parseClaimsJws(token)// 解析JWT
                .getBody();// 获取数据
        System.out.println(claims);
    }
}
