package com.meihaifeng.common;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/5.
 */
public class ValidateUtils {


    /**
     * 校验手机号
     * */
    public static boolean isMobileNO(Long mobiles){
        Pattern p = Pattern.compile("^((1[0-9])|(1[^4,\\D])|(1[0,5-9]))\\d{9}$");
        Matcher m = p.matcher(mobiles.toString());
        return m.matches();
    }

    /**
     * 判断文件是否图片格式
     * @param filename
     * @return boolean
     * regDemo.java
     * @author: ge.tao
     */
    public static boolean isImg(String filename){
        String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(filename.toLowerCase());
        return matcher.matches();
    }

    /**
     * 验证是否为空对象
     */
    public static boolean isNull(Object object){
        if (object instanceof List){
            if (((List)object).size()==0){
                return true;
            }
            else {
                return false;
            }
        }
        if (object == null || object==""){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 验证是否大于0
     */
    public static boolean isGreaterThan0(Integer result){
        if (result>0){
            return true;
        }
        else {
            return false;
        }
    }


}
