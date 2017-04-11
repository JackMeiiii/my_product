package com.meihaifeng.test;

import java.io.*;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/1/19
 * @description
 */
public class DataStreamTest {

    public static void main(String args[]) throws IOException {

        FileOutputStream fileOut = new FileOutputStream("DataStream.txt");
        DataOutputStream dataOut = new DataOutputStream(fileOut);

        try {
            dataOut.writeBoolean(true);
            dataOut.writeFloat(2.4f);
        }finally {
            dataOut.close();
        }

        FileInputStream fileIn = new FileInputStream("E:\\workplace\\my_product_official\\src\\main\\java\\com\\meihaifeng\\test\\StreamTest.java");
        DataInputStream dataIn = new DataInputStream(fileIn);

        try {
            System.out.println(dataIn.readBoolean());
            System.out.println(dataIn.readFloat());
        }finally {
            dataIn.close();
        }

    }
}
