package com.wkcto.controller;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/*
任涛 -----专用
*/
public class Collection {
    public static void main(String[] args) {
        /*List<String> list=new ArrayList();
        list.add("0");
        list.add("4");
        list.add("2");
        list.add("8");
        list.sort(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(list.toString());*/

        /*Set<String> set=new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        set.add("武汉");
        set.add("加油");
        set.add("中国");
        set.add("@@");
        System.out.println(set.toString());*/

        Map<String,String> map=new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        map.put("中国", "加油");
        map.put("盂县", "加油");
        map.put("山西", "加油");
        map.forEach((k,v)->{
            System.out.println("k->"+k+"v->"+v);
        });

    }
}
