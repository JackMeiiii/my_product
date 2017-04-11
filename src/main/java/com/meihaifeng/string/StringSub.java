package com.meihaifeng.string;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/28
 * @description
 */
public class StringSub {

    public static void main(String args[]) {
        String s1 = "MULTILINESTRING((120.054792624929 30.2568397438897,120.055818591248 30.2571742698074))";
        int len = s1.indexOf("((");
        String s2 = s1.substring(len+1,s1.length()-1);
        String s3 = s1.substring(len+1,s1.length()-1);
        //substring(int begin)包含开始
        //substring(int begin,int end)包括开始不包括结尾
        System.out.println(s3);
        s2 = s2.replaceFirst("\\(","[[").replaceFirst("\\)","]]").replaceAll(",","],[").replaceAll(" ",",");
        System.out.println(s2);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("12","123");
        String value = (String) concurrentHashMap.get("12");
    }
}
