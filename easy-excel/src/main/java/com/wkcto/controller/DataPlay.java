package com.wkcto.controller;

import com.alibaba.fastjson.JSONObject;
import com.wkcto.domain.Student;
import com.wkcto.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
任涛 -----专用
*/
@Controller
public class DataPlay {

    @Autowired
    @Qualifier("useStudentService")
    private StudentService studentService;

    @GetMapping("/play")
    @ResponseBody
    public List getAllList(){
        List<Student> students = studentService.allList();
        return students;
    }

    /**
     * 批量添加数据
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Integer addData(@RequestBody String jsonStr ){
        List<Student> list = JSONObject.parseArray(jsonStr, Student.class);
        return studentService.addBatchData(list);

    }

    /**
     * 批量删除数据
     * @return
     */
    @DeleteMapping("/delete")
    @ResponseBody
    public Integer deleteData(@RequestBody String jsonStr ){
        String ids= JSONObject.parseObject(jsonStr).getString("ids");
        Integer num =studentService.deleteBatchData(ids);
        return num;
    }

    /**
     * 批量更新数据
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    public Integer updateData(@RequestBody String jsonStr ){
        List<Student> list = JSONObject.parseArray(jsonStr, Student.class);
        return studentService.updateBatchData(list);

    }
}
