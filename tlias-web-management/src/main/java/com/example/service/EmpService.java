package com.example.service;


import com.example.entity.Emp;
import com.example.entity.PageResult;
import org.springframework.stereotype.Service;


public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize);
}
