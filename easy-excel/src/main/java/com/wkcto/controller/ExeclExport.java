package com.wkcto.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wkcto.domain.Student;
import com.wkcto.model.StudentModel;
import com.wkcto.service.StudentService;
import com.wkcto.util.ExcelExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExeclExport {

    @Autowired
    @Qualifier("useStudentService")
    private StudentService studentService;

    @GetMapping("/execl/export1")
    @ResponseBody
    public String  getExport1(){
        List<Student> students = studentService.allList();
        return students.toString();
    }

    @GetMapping("/execl/export")
    public void getExport(HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<Student> students = studentService.allList();
        List<StudentModel> data=new ArrayList<>();
        //转化为模型数据集合
        List<StudentModel> studentModels = JSONObject.parseArray(JSON.toJSONString(students), StudentModel.class);
        StudentModel studentModel = new StudentModel();
        ExcelExportUtil.export(studentModels,"学生表","student",studentModel,response,request);
    }
}
