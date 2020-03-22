package com.wkcto.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//import javax.persistence.Table;

@Data
//@Table(name = "student")
public class Student implements Serializable {
    //属性名和列名一样
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String sex;
    private Integer age;
    private Date crtTime;
    private Date updTime;


}
