package com.example.service;

import com.example.entity.PageResult;
import com.example.entity.Student;
import com.example.entity.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 分页查询所有学生信息
     * @param studentQueryParam
     * @return
     */
    PageResult<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 删除学生信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 添加学生信息
     * @param student
     */
    void add(Student student);

    /**
     * 获取学生信息(查询回显)
     * @param id
     * @return
     */
    Student getInfo(Integer id);

    /**
     * 修改学生信息
     * @param student
     */
    void update(Student student);

    /**
     * 修改学生违纪信息
     * @param student
     */
    void updateViolation(Student student);
}
