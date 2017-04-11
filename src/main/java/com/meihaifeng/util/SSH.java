package com.meihaifeng.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.jcraft.jsch.*;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/1/11
 * @description
 */
public class SSH {

    private static InputStream in = null;

    public static void main(String[] arg) {

        try {
            JSch jsch = new JSch();

            Session session = jsch.getSession("drore", "115.159.160.97", 22);

            String passwd = "Y*6xUE9dt?4%K";
            session.setPassword(passwd);

            UserInfo ui = new MyUserInfo() {
                public void showMessage(String message) {
                    JOptionPane.showMessageDialog(null, message);
                }

                public boolean promptYesNo(String message) {
                    return true;
                }
            };

            session.setUserInfo(ui);
            session.connect(30000);

            Channel channel = session.openChannel("shell");
            ((ChannelShell) channel).setPty(false);
            in = channel.getInputStream();
            OutputStream out = channel.getOutputStream();

            String command = "ls \n";

            channel.connect(3 * 1000);
            out.write(command.getBytes());
            out.flush();

            ReadThread readThread = new ReadThread();
            readThread.start();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static abstract class MyUserInfo implements UserInfo,
            UIKeyboardInteractive {
        public String getPassword() {
            return null;
        }

        public boolean promptYesNo(String str) {
            return false;
        }

        public String getPassphrase() {
            return null;
        }

        public boolean promptPassphrase(String message) {
            return false;
        }

        public boolean promptPassword(String message) {
            return false;
        }

        public void showMessage(String message) {
        }

        public String[] promptKeyboardInteractive(String destination,
                                                  String name, String instruction, String[] prompt, boolean[] echo) {
            return null;
        }
    }

    private static class ReadThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        List<String> list = new ArrayList<String>();




                        char c = (char) i;
                        System.out.print(c);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
