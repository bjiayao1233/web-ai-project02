package com.example.exception;

import com.example.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
// 全局异常处理类
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("服务器发生异常:",e);
        return Result.error("服务器发生异常");
    }

    @ExceptionHandler
    public Result handleDuplicatekeyException(DuplicateKeyException e){
        log.error("服务器发生异常:",e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry ");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2]+"已存在，请修改后重试！");
    }

}
