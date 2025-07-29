//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.exception;

import com.example.entity.Result;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("服务器发生异常:", e);
        return Result.error("服务器发生异常");
    }

    @ExceptionHandler
    public Result handleDuplicatekeyException(DuplicateKeyException e) {
        log.error("服务器发生异常:", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry ");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在，请修改后重试！");
    }
}
