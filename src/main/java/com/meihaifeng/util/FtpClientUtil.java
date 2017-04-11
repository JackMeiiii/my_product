package com.meihaifeng.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import sun.net.ftp.FtpClient;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/1/11
 * @description
 */
public class FtpClientUtil {

    private static String server="115.159.110.67";
    private static  int port = 21;
    private static  String userName = "drore";
    private static  String userPassword = "Y*6xUE9dt?4%K";
    private  static FtpClient ftpClient;

    public FtpClientUtil(String server,int port,String userName,String userPassword)
    {
        this.server=server;
        this.port=port;
        this.userName=userName;
        this.userPassword=userPassword;
    }
    /**
     * 链接到服务器
     *
     * @return
     */
    public static boolean open() {
        if (ftpClient != null && ftpClient.isConnected())
            return true;
        try {
            ftpClient = FtpClient.create();
            SocketAddress socketAddress = new InetSocketAddress(server,port);
            ftpClient.connect(socketAddress);
            ftpClient.login(userName,userPassword.toCharArray());
            ftpClient.setBinaryType();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ftpClient = null;
            return false;
        }
    }

    /**
     * 返回FTP目录下的文件列表
     *
     * @param ftpDirectory
     * @return
     */
    public static List<String> getFileNameList(String ftpDirectory) {
        List<String> list = new ArrayList<String>();
        if (!open())
            return list;
        try {
            BufferedReader dis = new BufferedReader(
                    new InputStreamReader(ftpClient.nameList(ftpDirectory)));
            String filename = "";
            while ((filename = dis.readLine()) != null) {
                list.add(filename);
                System.out.println(filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void main(String args[]) {
        List<String> lists = getFileNameList("/mnt/drore/gismanage/gis/webapps/ROOT/images");
        for (String str:lists){
            System.out.println(str);
        }
    	}

}
