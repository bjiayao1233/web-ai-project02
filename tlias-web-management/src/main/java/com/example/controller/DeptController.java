package com.example.controller;


import com.example.entity.Dept;
import com.example.entity.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j// 使用@Slf4j注解可以自动生成日志对象
@RestController
@RequestMapping("/depts")
public class DeptController {
   // private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    /*
     * 查询所有部门信息
     * controller问service获取数据，service调用mapper获取数据
     * */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)

    @GetMapping
    public Result list() {
        log.info("查询所有部门信息");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /*
     * 删除部门信息  --方式一：htt
     * */
   /* @DeleteMapping
    public Result delete(HttpServletRequest request) {
        String idstr = request.getParameter("id");// 获取请求参数
        int id = Integer.parseInt(idstr);// 将字符串转换成数字
        System.out.println("删除部门信息，id=" + id);
        return Result.success();// 返回成功
    }*/

    /*
     * 删除部门信息    方式二：@RequstParam
     * 注意：@RequestParam中的参数必须传递不传递会报错，如果需要不传递参数，将required参数值设置为flase
     * */
    /*@DeleteMapping
    public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
        System.out.println("删除部门信息，id=" + deptId);
        return Result.success();// 返回成功
    }*/

    /*
     * 删除部门信息   方式三：如果请求参数变量与形参变量名相同，则@RequestParam可以省略
     * controller将删除的id给service，service调用mapper删除数据
     * */

    @DeleteMapping
    public Result delete(Integer id) {
        //System.out.println("删除部门信息，id=" + id);
        log.info("删除部门信息，id={}",id);
        deptService.deleteById(id);
        return Result.success();// 返回成功


    }

    /*
     * 添加部门信息
     * controller将添加的id给service，service调用mapper删添加数据
     * */
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        //System.out.println("添加部门信息：" + dept);
        log.info("添加部门信息：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /*
     *根据id查询部门
     * */
    /*@GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer deptID){
        System.out.println("查询部门信息，id=" + deptID);
       // deptService.getById(deptID);
        return Result.success();
    }*/

    /*
     *根据id查询部门   简化
     * */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        //System.out.println("查询部门信息，id=" + id);
        log.info("查询部门信息，id={}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /*
     * 修改部门信息
     * */
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        //System.out.println("修改部门信息：" + dept);
        log.info("修改部门信息：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
