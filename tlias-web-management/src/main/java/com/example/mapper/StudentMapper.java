package com.example.mapper;

import com.example.entity.Student;
import com.example.entity.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {


    /**
     * 查询所有学生信息并通过pagehelper分页
     *
     * @param studentQueryParam
     * @return
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 删除学生信息
     *
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增学生信息
     *
     * @param student
     */
    void add(Student student);

    /**
     * 获取学生信息(查询回显)
     *
     * @param id
     * @return
     */
    Student getInfo(Integer id);

    /**
     * 修改学生信息
     *
     * @param student
     */
    void update(Student student);

    /**
     * 修改学生违纪信息
     *
     * @param student
     */
    void updateViolation(Student student);

    @MapKey("clazzName")
    List<Map<String, Object>> countStudentData();

    @MapKey("degree")
    List<Map<String, Object>> countStudentDegreeData();
}
