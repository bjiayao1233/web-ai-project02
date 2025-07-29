package com.example.mapper;

import com.example.entity.Clazz;
import com.example.entity.ClazzQueryParam;
import com.example.entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 查询所有班级信息并通过pagehelper分页
     * @param clazzQueryParam
     * @return
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);


    @Delete("delete from clazz where id= #{id}")
    void delete(Integer id); // 删除班级信息


    /**
     * 添加班级信息
     * @param clazz
     */
    void add(Clazz clazz);

    /**
     * 查询所有班级信息（在录入学生信息是选择班主任）
     * @return
     */
    @Select("select * from clazz")
    List<Clazz> findAll();

    /**
     * 根据id查询班级信息
     * @param id
     * @return
     */
    @Select("select *from clazz where id=#{id}")
    Clazz getById(Integer id);

    /**
     * 修改班级信息
     * @param clazz
     */
    void update(Clazz clazz);
}
