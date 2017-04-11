package com.meihaifeng.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/1/19
 * @description
 */
public class RandomStreamTest {


    /**
     * 1 readXXX()或writeXXX()：
     * 如ReadInt(), ReadLine(), WriteChar(), WriteDouble()等
     * 2 int skipBytes(int n)：将指针向下移动若干字节
     * 3 length()：返回文件长度
     * 4 long getFilePointer()：返回指针当前位置
     * 5 void seek(long pos)：将指针调到所需位置
     *
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {

        File file = new File("E:\\workplace\\my_product_official\\src\\main\\java\\com\\meihaifeng\\test\\StreamTest.java");

        if (file.exists()){
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
            randomAccessFile.seek(randomAccessFile.length());
            for (int i=0;i<5;i++){
                randomAccessFile.writeBytes("//Write"+new Date()+"\r\n");
            }

            randomAccessFile.seek(0);
            String line = randomAccessFile.readLine();
            while (line!=null){
                System.out.println(line);
                line=randomAccessFile.readLine();
            }
            randomAccessFile.close();
        }

    }
}
