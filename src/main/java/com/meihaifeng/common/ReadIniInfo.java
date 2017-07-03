package com.meihaifeng.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 　 on 2017/1/3.
 */
public class ReadIniInfo {
    private InputStream is;

    private Properties properties;

    //初始化配置文件
    public ReadIniInfo() {

        properties = new Properties();
        //读取配置文件：ftpconfig.properties

        is = this.getClass().getResourceAsStream("/config/ftpconfig.properties");

        try {

            properties.load(is);

        } catch (FileNotFoundException e) {

            System.out.println("配置文件不存在..");

            e.printStackTrace();

        } catch (IOException e) {

            System.out.println("读取配置文件错误：" + e.getMessage());

            e.printStackTrace();

        } finally {

            try {

                if (null != is) {

                    is.close();
                }

            } catch (IOException e) {

                System.out.println("关闭链接失败..");

                e.printStackTrace();
            }
        }
    }

    /**
     * 获取FTP服务器地址
     */
    public String getFtpServer() {

        return properties.getProperty("ftpServer");
    }

    /**
     * 获取FTP服务器端口
     */
    public String getFtpPort() {

        return properties.getProperty("ftpPort");
    }

    /**
     * 获取FTP用户账号
     */
    public String getFtpUser() {

        return properties.getProperty("ftpUser");
    }

    /**
     * 获取FTP用户密码
     */
    public String getFtpPwd() {

        return properties.getProperty("ftpPwd");
    }

    /**
     * 获取ftp目的地仓库地址
     */
    public String getFtpRemotePath() {

        return properties.getProperty("ftpRemotePath");
    }

    /**
     * http和ftp上传时的固定路径部份
     */
    public String getPathConstant() {

        return properties.getProperty("pathConstant");
    }

    /**
     * 图片资源管理图片路径
     */
    public String getImageCenter() {

        return properties.getProperty("imagecenter");
    }

    /**
     * 压缩图
     */
    public String getCompress() {

        return properties.getProperty("compress");
    }

    /**
     * 公文管理的附件路径
     */
    public String getGwgl() {
        return properties.getProperty("gwgl");
    }

}
