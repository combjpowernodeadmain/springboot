package com.qingnian.fanshe.domain;

import lombok.Data;

/*
任涛 -----专用
*/
@Data
public class GrandFather {
    //私有
    private String grandFatherName;
    //受保护的
    protected String grandFatherAge;
    //公共的
    public String grandFatherSex;
    //无修饰
    String grandFatherAddress;
}
