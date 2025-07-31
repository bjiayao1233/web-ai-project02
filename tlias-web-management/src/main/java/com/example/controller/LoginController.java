package com.example.controller;


import com.example.entity.Emp;
import com.example.entity.LoginInfo;
import com.example.entity.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private EmpService empService;
    /**
     * 登录
     *
     * @param emp
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录请求: {}", emp);
        LoginInfo info = empService.login(emp);
        if(info !=null){
            return Result.success(info);
        }
        return Result.error("用户名或密码错误");
    }
}
