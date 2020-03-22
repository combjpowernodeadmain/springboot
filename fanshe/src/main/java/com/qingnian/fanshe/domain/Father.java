package com.qingnian.fanshe.domain;

import lombok.Data;

/*
任涛 -----专用
*/
@Data
public class Father extends GrandFather{
    //私有
    private String fatherName;
    //受保护的
    protected String fatherAge;
    //公共的
    public String fatherSex;
    //无修饰
    String fatherAddress;
}
