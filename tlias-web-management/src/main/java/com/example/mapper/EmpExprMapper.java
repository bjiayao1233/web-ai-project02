package com.example.mapper;


import com.example.entity.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    /**
     * 批量插入员工基本信息
     * @param exprList
     */
    void insertBatch(List<EmpExpr> exprList) ;

    /**
     * 批量根据ID删除员工信息
     * @param empIds
     */
    void deleteByIds(List<Integer> empIds);
}
