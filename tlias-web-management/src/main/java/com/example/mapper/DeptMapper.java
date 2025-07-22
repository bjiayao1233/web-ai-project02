package com.example.mapper;


import com.example.entity.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /*
     * 如果实体类的名称和数据库表中的名称一样，那么就不需要写别名，如果不一样，那么就需要写别名
     * */
    //方法一：手动结果映射
    /*@Results({
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })*/
    //方法二:起别名
    /*
     * 查询所有的部门数据
     * */

    //@Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc;")
    @Select("select id, name, create_time , update_time from dept order by update_time desc;")
    public List<Dept> findAll();


    /*
     * 删除用户信息
     * */
    @Delete("delete from dept where id = #{id}")
    public void deleteByid(Integer id);

    /*
    * 新增部门信息
    * */
    @Insert("insert dept(name, create_time, update_time)values (#{name},#{createTime},#{updateTime})")
    public void add(Dept dept);


    /*
    * 根据id查询部门信息
    * */
    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept getById(Integer id);

    /*
    * 修改部门信息
    * */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
