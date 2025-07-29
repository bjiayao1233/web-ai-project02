package com.example.controller;


import com.example.entity.Emp;
import com.example.entity.EmpQueryParm;
import com.example.entity.PageResult;
import com.example.entity.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;
    @GetMapping("/list")
    public Result findAll(){
        List<Emp> list = empService.findAll();
        return Result.success(list);
    }

    @GetMapping
    public Result page(EmpQueryParm empQueryParm) {
        log.info("分页查询员工信息：{}",empQueryParm);
        PageResult<Emp> pageResult = empService.page(empQueryParm);
        return Result.success(pageResult);
    }

    /*
    * 将前端要添加的参数封装成Emp对象，调用service层保存员工信息（将员工信息传到save函数中）
    * */
    @PostMapping
    public Result save(@RequestBody Emp emp){
        empService.save(emp);
        return Result.success();
    }


    /**
     * 批量删除员工信息
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工信息，ids={}", ids);
        empService.delete(ids);
        return Result.success();
    }
    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getImfo(@PathVariable Integer id){
        log.info("根据id查询员工信息，id={}", id);
        Emp emp= empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     * @param emp
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息，emp={}", emp);
        empService.update(emp);
        return Result.success();
    }


}
