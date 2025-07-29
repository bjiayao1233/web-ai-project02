package com.example.controller;

import com.example.entity.*;
import com.example.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 查询所有班级
     * @return
     */
    @GetMapping("/list")
    public Result findAll() {
        List<Clazz> list = clazzService.findAll();
        return Result.success(list);
    }


    /**
     * 分页查询所需要班级信息
     * @return
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {// 前端将参数传递给接口，接口将参数封装成ClazzQueryParam对象
        log.info("分页查询班级信息：{}", clazzQueryParam);
        PageResult<Clazz> clazzlist = clazzService.page(clazzQueryParam);
        return Result.success(clazzlist);
    }

    /**
     * 删除班级信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        clazzService.delete(id);
        return Result.success();
    }

    /**
     * 添加班级信息
     * @param clazz
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级信息：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }


    /**
     * 根据id查询班级信息(查询回显)
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询班级信息为{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息
     * @param clazz
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        clazzService.update(clazz);
        return Result.success();
    }
}

