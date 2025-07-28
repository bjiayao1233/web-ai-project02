package com.example.service;


import com.example.entity.Emp;
import com.example.entity.EmpQueryParm;
import com.example.entity.PageResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {
    /*
     * 分页查询员工信息
     * */
    //PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin,LocalDate end);
    PageResult<Emp> page(EmpQueryParm empQueryParm);


    /*
    * 插入保存员工信息
    * */
    void save(Emp emp);

    /**
     * 根据员工Id批量删除员工信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工信息
     * @param emp
     */
    void update(Emp emp);
}

