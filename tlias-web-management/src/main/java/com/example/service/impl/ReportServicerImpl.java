package com.example.service.impl;

import com.example.entity.CountOption;
import com.example.entity.JobOption;
import com.example.mapper.EmpMapper;
import com.example.mapper.StudentMapper;
import com.example.service.EmpService;
import com.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ReportServicerImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper StudentMapper;

    @Override
    public JobOption getEmpJobData() {
        //调用mapper接口获取数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        //创建两个list，分别存放职位名称和职位数量
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public CountOption getStudentCountData() {
        List<Map<String, Object>> list = StudentMapper.countStudentData();//调用mapper接口获取数据clazzName,sum封装到list中
        List<Object> clazzList =list.stream().map(dataMap-> dataMap.get("clazzName")).toList();
        List<Object> dataList =list.stream().map(dataMap-> dataMap.get("sum")).toList();
        return new CountOption(clazzList, dataList);
    }
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return StudentMapper.countStudentDegreeData();
    }

}
