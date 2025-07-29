package com.example.service.impl;

import com.example.entity.*;
import com.example.mapper.ClazzMapper;
import com.example.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;


    /**
     * 分页查询所有班级信息
     * @param clazzQueryParam
     * @return
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数（pagehelper：获取到分页开始和分页大小，拦截下边执行的sql语句自动执行分页语句）
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //2.执行   分页   查询
        List<Clazz> list = clazzMapper.list(clazzQueryParam);
        //3.解析查询结果
        Page<Clazz> p=(Page<Clazz>) list;// 将pagehelper的分页结果转为mybatis的分页结果
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    /**
     * 删除班级信息
     * @param id
     */
    @Override
    public void delete(Integer id) {
        clazzMapper.delete(id);
    }

    /**
     * 添加班级信息
     * @param clazz
     */
    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }

    /**
     * 查询所有班级信息
     * @return
     */
    @Override
    public List<Clazz> findAll() {
        List<Clazz> list = clazzMapper.findAll();
        return list;
    }

    /**
     * 根据id查询班级信息（查询回显）
     * @param id
     * @return
     */
    @Override
    public Clazz getById(Integer id) {
        Clazz clazz= clazzMapper.getById(id);
        return clazz;
    }

    /**
     * 修改班级信息
     * @param clazz
     */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }


}
