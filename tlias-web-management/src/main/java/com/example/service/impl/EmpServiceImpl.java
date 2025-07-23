package com.example.service.impl;



import com.example.entity.Emp;
import com.example.entity.PageResult;
import com.example.mapper.EmpMapper;
import com.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        long total= empMapper.count();
        int start =(page-1)*pageSize;
        List<Emp> emp= empMapper.list(start,pageSize);
        return new PageResult<>(total,emp);
    }
}
