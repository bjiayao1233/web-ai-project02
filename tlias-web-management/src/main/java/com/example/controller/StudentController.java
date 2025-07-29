package com.example.controller;


import com.example.entity.PageResult;
import com.example.entity.Result;
import com.example.entity.Student;
import com.example.entity.StudentQueryParam;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    /**
     * 分页查询所有学生信息
     *
     * @param studentQueryParam
     * @return
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询所有学生信息");
        PageResult<Student> pageResult = studentService.list(studentQueryParam);
        return Result.success(pageResult);

    }

    /*
    * @RequestBody：接收请求体（body）里的 JSON/XML 数据，自动反序列化为对象。
      @PathVariable：接收 URL 路径中的变量，如 /emps/{id}。
    * */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除学生信息，ids={}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 添加学生信息
     *
     * @param student
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("添加学生信息：{}", student);
        studentService.add(student);
        return Result.success();
    }

    /**
     * 查询学生信息(查询回显)
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询学生信息，id={}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        studentService.update(student);
        return Result.success();
    }

    /**
     * 修改学生违纪信息
     * @param id
     * @param score
     * @return
     */
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable short score){
        Student student=new Student();
        student.setId(id);
        student.setViolationScore(score);
        studentService.updateViolation(student);
        return Result.success();

    }



}
