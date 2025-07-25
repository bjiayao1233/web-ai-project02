package com.example.service.impl;


import com.example.entity.Dept;
import com.example.mapper.DeptMapper;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /*
    * 查询所有部门信息
    * */
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }


    /*
    * 根据id删除部门
    * */
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteByid(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Override
    public Dept getById(Integer id) {

        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
