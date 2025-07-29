package com.example.controller;


import com.example.entity.CountOption;
import com.example.entity.Result;
import com.example.entity.JobOption;
import com.example.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 员工职位数据
     *
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位数据");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 员工性别数据
     *
     * @return
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别数据");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 学生所选课程统计
     *
     * @return
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData() {
        log.info("统计学生数据");
        // 调用服务层方法获取学生统计数据
        CountOption countOption = reportService.getStudentCountData();
        return Result.success(countOption);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("统计学生学历数据");
        List<Map<String, Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }


}
