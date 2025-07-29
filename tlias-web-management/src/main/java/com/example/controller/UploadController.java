package com.example.controller;


import com.example.entity.Result;
import com.example.utils.AliyunOSSOperator;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    /**
     * 文件上传
     * @param
     * @param
     * @param
     * @return
     * @throws IOException
     */
    /*@PostMapping
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {

        log.info("上传文件，name={},age={},file={}", name, age, file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newfilename = UUID.randomUUID().toString() + extension;
        file.transferTo(new File("D:/images/" + newfilename));
        return Result.success();
    }*/

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 文件上传到阿里云OSS
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("file")MultipartFile file) throws Exception {
        log.info("文件上传，{}" ,file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传成功，{}" ,url);
        return Result.success(url);
    }
}
