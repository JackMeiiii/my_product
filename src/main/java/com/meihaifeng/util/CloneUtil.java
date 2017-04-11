package com.meihaifeng.util;

import java.io.*;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/4/5
 * @description
 */
public class CloneUtil {
        @SuppressWarnings("unchecked")
        public static <T extends Serializable> T clone(T   obj){
            T cloneObj = null;
            try {
                //写入字节流
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream obs = new   ObjectOutputStream(out);
                obs.writeObject(obj);
                obs.close();

                //分配内存，写入原始对象，生成新对象
                ByteArrayInputStream ios = new  ByteArrayInputStream(out.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(ios);
                //返回生成的新对象
                cloneObj = (T) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cloneObj;
        }
    }
