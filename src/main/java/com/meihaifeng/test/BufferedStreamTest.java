package com.meihaifeng.test;

import java.io.*;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/1/19
 * @description
 */
public class BufferedStreamTest {
    public static void main(String args[]) {
        File inFile = new File("E:\\workplace\\my_product_official\\src\\main\\java\\com\\meihaifeng\\test\\StreamTest.java");
        File outFile = new File("BufferedStreamTest");

        try {
            BufferedInputStream buffis = new BufferedInputStream(new FileInputStream(inFile));
            BufferedOutputStream buffos = new BufferedOutputStream(new FileOutputStream(outFile));

            int len = 0;
            byte arr[] = new byte[256];
            do {
                len = buffis.read(arr);
                buffos.write(arr,0,len);
            }while (len==256);
                buffis.close();
                buffos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
