package com.meihaifeng.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.Set;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2016/9/2.
 * email：6492178@gmail.com
 * description:
 */
public class MyStringUtils {

    public static boolean isNotEmpty(Object... strings) {
        for (Object string : strings) {
            if (string instanceof Collection){
                for (Object str:(Collection<Object>)string){
                    if (str == null || "".equals(str)||"null".equals(str)) {
                        return false;
                    }
                }
            }
            if (string instanceof Set){
                for (Object str:(Set<Object>)string){
                    if (str == null || "".equals(str)||"null".equals(str)) {
                        return false;
                    }
                }
            }
            if (string instanceof String){
                if (string == null || "".equals(string)||"null".equals(string)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String args[]) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("","123");
        jsonObject.put("1243","1234");
        Set<String> keys = jsonObject.keySet();
        for (String str:keys){
            System.out.println(str);
        }
        Collection<Object> list = jsonObject.values();
        String[] strs = {"1111","112","1113"};
        int[] ints = {1,2,3};
        isNotEmpty(strs);
        isNotEmpty(keys);
        isNotEmpty(list);
        isNotEmpty(ints);
        System.out.println("Hello World!");
    }
}
