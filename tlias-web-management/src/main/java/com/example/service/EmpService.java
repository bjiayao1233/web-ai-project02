package com.example.service;


import com.example.entity.Emp;
import com.example.entity.EmpQueryParm;
import com.example.entity.PageResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


public interface EmpService {
    /*
     * 分页查询员工信息
     * */
    //PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin,LocalDate end);
    PageResult<Emp> page(EmpQueryParm empQueryParm);
}
