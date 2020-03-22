package com.wkcto.controller;

import com.wkcto.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
public class UpLoadExcelFileController {

    @Autowired
    @Qualifier("studentServiceJdbc")
    private StudentService studentService;


    @PostMapping(value = "uploadExcel")
    @ResponseBody
    public String uploadFile(MultipartFile file, HttpServletRequest req){
        System.out.println("============UpLoadFileControllerFileUpload=============");
        //定义存储文件的目录，上传的文件按日期生成目录，每一个目录是一天的文件。
        //例如 文件夹文件2019-07-01 ， 7月1日上传的文件。
        //String path = req.getServletContext().getRealPath("/uploadFile/");
        String path = "D:/uploadFile/";
        //用日期生成文件夹
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formate_date = format.format(date); //2019-07-01

        //创建目录
        File folder = new File(path,formate_date);
        if( !folder.isDirectory()){
            folder.mkdirs();
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-","");

        String oldName = file.getOriginalFilename();

        String newName = uuid + oldName.substring( oldName.indexOf("."), oldName.length());
        String ret = "";
        try{
            //创建保存到服务器磁盘的文件对象
            File outFile = new File(folder,newName);
            //把上传文件保存到outFile中
            file.transferTo(outFile);
            //准备是调用Service， 把Excel文件传入到Service交给EasyExcel处理
            studentService.readExcel(outFile);

            ret = "上传文件并入库成功！！！";

        }catch(Exception e){
            e.printStackTrace();
            ret = "文件上传失败";
        }

        return ret;


    }
}
