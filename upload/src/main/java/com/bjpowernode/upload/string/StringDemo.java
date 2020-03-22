package com.bjpowernode.upload.string;

/*
任涛 -----专用
*/
public class StringDemo {
    public static void main(String[] args) {
        String string="";
        System.out.println(string.isEmpty());
    }
}
class save{
    public static void main(String[] args) {
        String string=" w a ";
        //任意一个字母或数字或下划线，也就是 A~Z,a~z,0~9,_ 中任意一个
        System.out.println(string.matches("\\w"));
    }
}
