package com.example.service.impl;


import com.example.entity.Emp;
import com.example.entity.EmpQueryParm;
import com.example.entity.PageResult;
import com.example.mapper.EmpMapper;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Emp> page(EmpQueryParm empQueryParm) {
        //1.设置分页参数（pagehelper）
        PageHelper.startPage(empQueryParm.getPage(), empQueryParm.getPageSize());
        //2.执行查询
        List<Emp> list = empMapper.list(empQueryParm);
        //3.解析查询结果
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    /* *//**
     * 原始分页查询操作
     * *//*
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        long total= empMapper.count();
        int start =(page-1)*pageSize;
        List<Emp> emp= empMapper.list(start,pageSize);
        return new PageResult<>(total,emp);}*/

    /**
     * 基于pagehelper分页查询
     *
     * 注意事项：
     *       1.定义的sql语句结尾不能加;
     *       2.pagehelper的分页参数只会对下一条查询有效
     */
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin, LocalDate end) {
        //1.设置分页参数（pagehelper）
        PageHelper.startPage(page, pageSize);
        //2.执行查询
        List<Emp> list = empMapper.list(name, gender, begin, end);
        //3.解析查询结果
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<>(p.getTotal(),p.getResult());
    }*/
}
