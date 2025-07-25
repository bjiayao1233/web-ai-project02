package com.example.controller;


import com.example.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @PostMapping
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {

        log.info("上传文件，name={},age={},file={}", name, age, file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newfilename = UUID.randomUUID().toString() + extension;
        file.transferTo(new File("D:/images/" + newfilename));

        return Result.success();

    }
}
