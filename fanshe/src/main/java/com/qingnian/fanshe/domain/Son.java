package com.qingnian.fanshe.domain;

import lombok.Data;

/*
任涛 -----专用
*/
@Data
public class Son extends Father{
    //私有
    private String sonName;
    //受保护的
    protected String sonAge;
    //公共的
    public String sonSex;
    //无修饰
    String sonAddress;
}
