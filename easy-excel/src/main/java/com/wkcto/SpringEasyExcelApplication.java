package com.wkcto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.wkcto.dao")
@SpringBootApplication
public class SpringEasyExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEasyExcelApplication.class, args);
    }


}
