package com.meihaifeng.util;

import java.io.File;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/1/11
 * @description
 */
public class GetFilesInFolderImpl {

    public static void main(String args[]) {
    		getFilesInFolder();
    }

    public static void getFilesInFolder(){
        String path = "http://oss.drore.com/material/gis/labels/publics/defaults";
        File file = new File(path);
        if (!file.exists()){
            System.out.println(path+"   not exists");
            return;
        }

        File[] files = file.listFiles();
        for (int i=0;i<files.length;i++){
            File file_each = files[i];
            if (file_each.isDirectory()){
                System.out.println(file_each.getName()+"  [目录]");
            }else {
                System.out.println(file_each.getName());
            }
        }

    }

}
