package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private Integer page=1; // 当前页码
    private Integer pageSize=10; // 每页记录数
    private String name;
    private Integer degree; // 班级ID
    private Integer clazzId;
}
