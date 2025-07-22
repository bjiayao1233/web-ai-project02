package com.example.service;


import com.example.entity.Dept;

import java.util.List;

public interface DeptService {
    /*
     *查询所有部门信息
     * */
    List<Dept> findAll();

    /*
    * 删除部门信息
    * */
    void deleteById(Integer id);

    /*
    * 添加部门信息
    * */
    void add(Dept dept);

    /*
    * 根据id查询部门
    * */
    public Dept getById(Integer id);

    /*
    * 修改部门
    * */
    void update(Dept dept);
}
