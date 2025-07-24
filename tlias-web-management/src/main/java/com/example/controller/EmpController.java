package com.example.controller;


import com.example.entity.Emp;
import com.example.entity.EmpQueryParm;
import com.example.entity.PageResult;
import com.example.entity.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpQueryParm empQueryParm) {
        log.info("分页查询员工信息：{}",empQueryParm);
        PageResult<Emp> pageResult = empService.page(empQueryParm);
        return Result.success(pageResult);
    }


}
