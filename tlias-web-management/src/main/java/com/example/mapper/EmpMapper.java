package com.example.mapper;


import com.example.entity.Emp;
import com.example.entity.EmpQueryParm;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /*  -------------------------原始分页查询-------------------------
     *//**
     * 查询员工数量
     *//*
    @Select("select count(*) sum from emp left join dept on emp.dept_id=dept.id")
    public long count();
    */

    /**
     * 分页查询
     *//*
    @Select("select emp.*,dept.name deptName from emp left join dept on emp.dept_id=dept.id order by emp.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);*/
    /*public List<Emp> list(String name,
                          Integer gender,
                          LocalDate begin,
                          LocalDate end
    );*/
    public List<Emp> list(EmpQueryParm empQueryParm);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert ("     insert emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values ( #{username},#{name},#{gender}, #{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);
}
