package com.meihaifeng.util;

import com.meihaifeng.entity.User;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2016/12/23
 * @description
 */
public class ExportToExcel {

    public static void main(String args[]) {
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        for (int i=10;i<20;i++){
            User user = new User(String.valueOf(i),"meihf","密码");
            list1.add(user);
        }

        for (int ii = 0;ii<10;ii++){
            User user = new User(String.valueOf(ii),"meihf","密码");
            list2.add(user);
        }

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("用户表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("id");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("密码");
        cell.setCellStyle(style);

        for (int i = 0; i < list2.size()+list1.size(); i++)
        {
            if (i<list2.size()){
                row = sheet.createRow((int) i + 1);
                User user = (User) list2.get(i);
                // 第四步，创建单元格，并设置值
                row.createCell((short) 0).setCellValue(user.getId());
                row.createCell((short) 1).setCellValue(user.getName());
                row.createCell((short) 2).setCellValue(user.getPassword());
            }else {
                row = sheet.createRow((int) i + 1);
                User user = (User) list1.get(i-list2.size());
                // 第四步，创建单元格，并设置值
                row.createCell((short) 0).setCellValue(user.getId());
                row.createCell((short) 1).setCellValue(user.getName());
                row.createCell((short) 2).setCellValue(user.getPassword());
            }

        }


        // 第六步，将文件存到指定位置
        try
        {
            FileOutputStream fout = new FileOutputStream("users3.xls");
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
