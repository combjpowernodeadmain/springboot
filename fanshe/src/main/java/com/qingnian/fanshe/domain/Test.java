package com.qingnian.fanshe.domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
任涛 -----专用
*/
public class Test {
    public static void main(String[] args) {
        List<Field> list = getAllField(new Son());
        for (Field field : list) {
            System.out.println(field);
        }
    }
    private static List<Field> getAllField(Object model) {
        Class clazz = model.getClass();
        List<Field> fields = new ArrayList<>();
        //只要父类存在，就获取其类的属性到集合
        while (clazz != null) {
            fields.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            //获取其父类
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}
