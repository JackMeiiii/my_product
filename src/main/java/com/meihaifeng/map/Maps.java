package com.meihaifeng.map;

import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/29
 * @description
 */
public class Maps {

    public static void main(String args[]) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(3);
        linkedHashMap.put("12", 23);
        linkedHashMap.put("212", 123);
        linkedHashMap.put("412", 523);
        linkedHashMap.put("312", 13);
        System.out.println(linkedHashMap.toString());

        TreeMap treeMap = new TreeMap();
        treeMap.put("12", 23);
        treeMap.put("212", 123);
        treeMap.put("412", 523);
        treeMap.put("312", 13);
        System.out.println(treeMap.toString());
    }
}
