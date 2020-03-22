package com.wkcto.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.wkcto.model.PageInfo;
import com.wkcto.model.ViewStudentModel;
import com.wkcto.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class DownLoadFile {

    @Autowired
    @Qualifier("useStudentService")
    private StudentService studentService;

    //下载excel文件
    @RequestMapping("/downexcel")
    public void downLoad(HttpServletResponse response) throws IOException {
        //获取输出对象
        ServletOutputStream out = response.getOutputStream();
        //设置下载的参数
        response.setContentType("multipart/form-data");
        //字符集
        response.setCharacterEncoding("utf-8");

        //创建文件名称
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String strDate = formatter.format(date);

        String tempName = "学生数据-"+strDate;
        String fileName = new String(tempName.getBytes(),"utf-8" );

        //设置请求header
        response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");

        //使用easy-excel
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,true);

        //创建Sheet
        Sheet sheet = new Sheet(1,0,ViewStudentModel.class);

        //获取数据
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNo(0);
        pageInfo.setPageSize(100);
        List<ViewStudentModel> students = studentService.queryStudents(pageInfo);

        writer.write(students,sheet);

        writer.finish();

        out.close();

    }
}
