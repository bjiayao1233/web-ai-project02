package com.example.service.impl;


import com.example.entity.*;
import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.service.EmpLogService;
import com.example.service.EmpService;
import com.example.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    /**
     * 分页查询员工信息
     *
     * @param empQueryParm
     * @return
     */
    @Override
    public PageResult<Emp> page(EmpQueryParm empQueryParm) {
        //1.设置分页参数（pagehelper）
        PageHelper.startPage(empQueryParm.getPage(), empQueryParm.getPageSize());
        //2.执行查询
        List<Emp> list = empMapper.list(empQueryParm);
        //3.解析查询结果
        Page<Emp> p = (Page<Emp>) list;//将pagehelper的分页结果转为mybatis的分页结果
        return new PageResult<>(p.getTotal(), p.getResult());
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
     * <p>
     * 注意事项：
     * 1.定义的sql语句结尾不能加;
     * 2.pagehelper的分页参数只会对下一条查询有效
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

    /**
     * 保存员工信息和员工经历（使用@Transactional注解使得两个插入同时成功）
     *
     * @param emp
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        //输入信息
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            //如果有工作经历信息，则插入工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                    System.out.println(empExpr.getEmpId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            EmpLog empLog = new EmpLog(emp.getId(), LocalDateTime.now(), "新增员工信息" + emp);
            empLogService.insertLog(empLog);
        }
    }

    /**
     * 批量删除员工信息和员工经历（使用@Transactional注解使得两个插入同时成功）
     *
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void delete(List<Integer> ids) {
        //批量删除员工基本信息
        empMapper.deleteByIds(ids);
        //批量删除员工工作经历
        empExprMapper.deleteByIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改员工信息和员工经历
     *
     * @param emp
     */
    @Override
    public void update(Emp emp) {
        //emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //1.根据Id修改员工基本信息
        empMapper.update(emp);
        //2.修改工作经历信息   (1)根据id删除所有员工工作经历  （2）再添加员工新的工作经历（将一个员工的所有工作经历绑定成一个id）
        empExprMapper.deleteByIds(Arrays.asList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    /**
     * 查询所有员工信息
     *
     * @return
     */
    @Override
    public List<Emp> findAll() {
        List<Emp> list = empMapper.findAll();
        return list;
    }

    /**
     * 登录
     * @param emp
     * @return
     */
    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        if (e != null) {
            Map<String, Object> claims =new HashMap<>();
             claims.put("id",e.getId());
             claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            log.info("");
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(), jwt);
        }
        return null;
    }

}
