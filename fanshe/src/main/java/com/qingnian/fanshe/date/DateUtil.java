package com.qingnian.fanshe.date;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
任涛 -----专用
*/
public class DateUtil {
    public static void main(String[] args) throws ParseException {
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(format.format(new Date()));
//        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//        System.out.println(format.parse("2010年4月23日 9时34分12秒"));


        /*DateTimeFormatter pattern1 = DateTimeFormatter.ofPattern("HH秒mm:ss");
        System.out.println(LocalTime.now().format(pattern1));
        String s = LocalTime.now().format(pattern1);
        System.out.println(s);*/
//        System.out.println(LocalDate.now().format(pattern));
//        System.out.println(LocalDateTime.now().format(pattern));
//        System.out.println(LocalDateTime.now());
//        System.out.println(LocalDate.now());
//        System.out.println(LocalTime.now());
        /*DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println(LocalDateTime.parse("2010年04月23日 09时34分12秒", pattern));*/


        /*DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        System.out.println(LocalDate.parse("2010年04月23日", pattern));*/

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("HH时mm分ss秒");
        System.out.println(LocalTime.parse("09时34分12秒", pattern));
        LocalTime time = LocalTime.parse("09时34分12秒", pattern);
    }
}
