package com.example.mapper;


import com.example.entity.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * 查询员工数量
     */
    @Select("select count(*) sum from emp left join dept on emp.dept_id=dept.id")
    public long count();
    /**
     *分页查询
     */
    @Select("select emp.*,dept.name deptName from emp left join dept on emp.dept_id=dept.id order by emp.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);
}
