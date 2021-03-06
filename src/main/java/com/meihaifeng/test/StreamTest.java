package com.meihaifeng.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/1/19
 * @description
 */
public class StreamTest {

    public static void main(String args[]) {
        try {
            long start = System.currentTimeMillis();
            FileReader fileReader = new FileReader("E:\\workplace\\my_product_official\\src\\main\\java\\com\\meihaifeng\\test\\StreamTest.java");
            FileWriter fileWriter = new FileWriter("streamWriter.txt");
            char[] buffer = new char[3000];
            int charsRead;
            charsRead = fileReader.read(buffer);

            while (charsRead!=-1){
                fileWriter.write(buffer,0,charsRead);
                charsRead = fileReader.read(buffer);
            }
            fileReader.close();
            fileWriter.close();
            long end = System.currentTimeMillis();
            System.out.println(end-start);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e2){
            e2.printStackTrace();
        }
    }
}

