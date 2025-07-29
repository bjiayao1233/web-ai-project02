package com.example.service;

import com.example.entity.*;

import java.util.List;

public interface ClazzService {

    /**
     * 查询所有班级信息并分页
     *
     * @return List of Clazz
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 删除班级信息
      * @param id
     */
    void delete(Integer id);

    /**
     * 添加班级信息
     * @param clazz
     */
    void add(Clazz clazz);


    /**
     * 查询所有班级信息
     * @return
     */
    List<Clazz> findAll();

    /**
     * 根据ID查询班级信息（查询回显）
     * @param id
     * @return
     */
    Clazz getById(Integer id);

    /**
     * 修改班级信息
     * @param clazz
     */
    void update(Clazz clazz);
}
