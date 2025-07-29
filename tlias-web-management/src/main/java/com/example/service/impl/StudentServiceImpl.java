package com.example.service.impl;


import com.example.entity.PageResult;
import com.example.entity.Student;
import com.example.entity.StudentQueryParam;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询所有学生信息
     * @param studentQueryParam
     * @return
     */
    @Override
    public PageResult<Student> list(StudentQueryParam studentQueryParam) {
        //分页查询
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        //执行sql语句
        List<Student> list = studentMapper.list(studentQueryParam);
        //解析查询结果
        Page<Student> p = (Page<Student>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /**
     * 删除学生信息
     * @param ids
     */
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    /**
     * 新增学生信息
     * @param student
     */
    @Override
    public void add(Student student) {
        student.setId(1);
        studentMapper.add(student);


    }
    /**
     * 获取学生信息(查询回显)
     * @param id
     * @return
     */
    @Override
    public Student getInfo(Integer id) {
        Student student= studentMapper.getInfo(id);
        return student;
    }

    /**
     * 修改学生信息
     * @param student
     */
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }


    /**
     * 修改学生违纪信息
     * @param student
     */
    @Override
    public void updateViolation(Student student) {
        studentMapper.updateViolation(student);
    }
}
