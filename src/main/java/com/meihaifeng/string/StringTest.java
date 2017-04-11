package com.meihaifeng.string;

import java.util.Vector;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/27
 * @description
 */
public class StringTest {

    public static String appendStr(String s){
        s+="bbb";
        return s;
    }

    public static StringBuilder appendSb(StringBuilder ss){
        return ss.append("bbb");
    }

    public static void main(String args[]) {

        String s = new String("aaa");
        String s1 = StringTest.appendStr(s);
        System.out.println("String aaa>>>"+s1.toString());
        System.out.println("String aaa>>>"+s.toString());


        StringBuilder sb = new StringBuilder("aaa");
        StringBuilder s2 = StringTest.appendSb(sb);
        System.out.println("StringBuilder aaa>>>"+s2.toString());
        System.out.println("StringBuilder aaa>>>"+sb.toString());
    }

}
