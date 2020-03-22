package com.wkcto.config;


import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @Configuration:放在类的上面
 * 表示当前类相当于spring的xml配置文件
 * 在当前类中，定义方法，方法的返回值是对象
 * 对象就等同于xml中的<bean></bean>
 */
@Configuration
public class MyAppConfig {
    //创建上传文件解析器MultipartResolver接口的实现类对象

    @Bean
    public MultipartConfigElement multipartConfigElement(){

        //创建解析器对象
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //指定配置参数， 是application.properties文件中的内容
        //上传文件的临时目录
        factory.setLocation("D:/temp");
        //当个文件的最大值
        //factory.setMaxFileSize(DataSize.of(1, DataUnit.MEGABYTES));
        factory.setMaxFileSize(DataSize.ofMegabytes(1L));
        //上传多个文件总的大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(10L));
        //上传文件直接写入磁盘
        factory.setFileSizeThreshold(DataSize.ofBytes(0L));

        //返回解析器
        return factory.createMultipartConfig();
    }
}
