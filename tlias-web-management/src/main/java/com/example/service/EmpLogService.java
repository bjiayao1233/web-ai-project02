package com.example.service;


import com.example.entity.EmpLog;

public interface EmpLogService {

    /**
     * 插入员工日志信息
     * @param empLog
     */
    public void insertLog(EmpLog empLog);

}
