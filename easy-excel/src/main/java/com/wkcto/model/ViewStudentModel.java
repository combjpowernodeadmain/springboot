package com.wkcto.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ViewStudentModel extends BaseRowModel {

    //定义属性， 指定列名， 列的位置
    // value:列名； index：列的索引位置，从 0 开始

    @ExcelProperty(value = "学生编号",index = 0)
    private Integer id;
    @ExcelProperty(value = "姓名",index = 1)
    private String name;
    @ExcelProperty(value = "邮箱",index = 2)
    private String email;
    @ExcelProperty(value = "年龄",index = 3)
    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ViewStudentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
