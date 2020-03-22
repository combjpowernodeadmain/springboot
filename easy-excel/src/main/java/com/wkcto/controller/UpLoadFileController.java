package com.wkcto.controller;

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
public class UpLoadFileController {



    /**
     * 定义方法，实现文件上传
     * MultipartFile :表示上传的文件对象---html中的<input type="file"></iput>
     */

    /**
     *
     * @param file MultipartFile 上传的文件
     * @return String 文件在服务器上的路径，通过这个路径可以访问文件
     */
    @PostMapping(value = "upload")
    @ResponseBody
    public String uploadFile(MultipartFile file, HttpServletRequest req){
        //定义存储文件的目录，上传的文件按日期生成目录，每一个目录是一天的文件。
        //例如 文件夹文件2019-07-01 ， 7月1日上传的文件。
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

        //处理文件：需要重新命名文件。UUID标识文件
        //uuid : 324uosu-jkjo22-joui2
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //上传的文件名称 a.png
        String oldName = file.getOriginalFilename();
        //生成新的文件名称 : uuid + 原来文件的扩展名 png
        String newName = uuid + oldName.substring( oldName.indexOf("."), oldName.length());
        String ret = "";
        try{
            //创建保存到服务器磁盘的文件对象
            File outFile = new File(folder,newName);
            //把上传文件保存到outFile中
            file.transferTo(outFile);

            //拼接文件的路径
            ret = req.getScheme()+"://" + req.getServerName() + ":"
                    + req.getServerPort() +"/uploadFile/" + formate_date +"/" + newName;


        }catch(Exception e){
            e.printStackTrace();
            ret = "文件上传失败";
        }

        return ret;


    }
}
