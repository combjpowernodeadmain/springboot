package com.bjpowernode.upload.bigdecimal;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/*
任涛 -----专用
*/
public class DecimalUtil {
    public static BigDecimal add(double num1, double num2){
        BigDecimal bignum1 = new BigDecimal(num1);
        BigDecimal bignum2 = new BigDecimal(num2);
        return bignum1.add(bignum2);
    }
    public static BigDecimal subtract(double num1, double num2){
        BigDecimal bignum1 = new BigDecimal(num1);
        BigDecimal bignum2 = new BigDecimal(num2);
        return bignum1.subtract(bignum2);
    }
    public static BigDecimal multiply(double num1, double num2){
        BigDecimal bignum1 = new BigDecimal(num1);
        BigDecimal bignum2 = new BigDecimal(num2);
        return bignum1.multiply(bignum2);
    }
    public static BigDecimal divide(double num1, double num2){
        BigDecimal bignum1 = new BigDecimal(num1);
        BigDecimal bignum2 = new BigDecimal(num2);
        return bignum1.divide(bignum2,1,4);
    }

    public static void main(String[] args) {
        //System.out.println(DecimalUtil.divide(7.75,5));
        double pi=3.1415927;//圆周率
        //以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.####%").format(pi));//314.16%
    }
}
