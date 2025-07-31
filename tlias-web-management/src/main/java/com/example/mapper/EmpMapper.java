package com.example.mapper;


import com.example.entity.Emp;
import com.example.entity.EmpQueryParm;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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

    /**/
    public List<Emp> list(EmpQueryParm empQueryParm);//参数对象

    /**
     * 新增员工基本信息
     *
     * @param emp
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("     insert emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values ( #{username},#{name},#{gender}, #{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    /**
     * 根基ID删除员工信息
     *
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据员工Id查询员工信息（查询回显）
     *
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 修改员工信息
     *
     * @param emp
     */
    void update(Emp emp);


    /**
     * 查询员工职位数据
     *
     * @return
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 查询员工性别数据
     *
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 查询所有员工
     *
     * @return
     */
    @Select("select * from emp")
    List<Emp> findAll();

    @Select("select  id,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
