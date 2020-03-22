package com.wkcto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
public class MultiUpLoadFileController {

    @PostMapping(value = "multiUpload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile[] multipartFiles, HttpServletRequest req){
        System.out.println("MultiUpLoadFileController===" + multipartFiles.length);
        String path = req.getServletContext().getRealPath("/uploadFile/");
        //用日期生成文件夹
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formate_date = format.format(date); //2019-07-01

        //创建目录
        File folder = new File(path,formate_date);
        if( !folder.isDirectory()){
            folder.mkdirs();
        }

        StringBuilder builder = new StringBuilder();
        String ret = "";
        for(MultipartFile mulfile : multipartFiles ){
            //处理文件：需要重新命名文件。UUID标识文件
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //上传的文件名称 a.png
            String oldName = mulfile.getOriginalFilename();
            //生成新的文件名称 : uuid + 原来文件的扩展名 png
            String newName = uuid + oldName.substring( oldName.indexOf("."), oldName.length());

            try{
                //创建保存到服务器磁盘的文件对象
                File outFile = new File(folder,newName);
                //把上传文件保存到outFile中
                mulfile.transferTo(outFile);

                //拼接文件的路径
                ret = req.getScheme()+"://" + req.getServerName() + ":"
                        + req.getServerPort() +"/uploadFile/" + formate_date +"/" + newName;


            }catch(Exception e){
                e.printStackTrace();
                ret = "文件上传失败";
            }
            builder.append(ret).append(",");
        }
        return builder.toString();


    }
}
