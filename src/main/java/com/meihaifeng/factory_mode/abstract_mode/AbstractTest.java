package com.meihaifeng.factory_mode.abstract_mode;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/2/7
 * @description
 */
public class AbstractTest {
    public static void main(String args[]) {
        Provider provider = new SendMailFactory();
         Sender sender = provider.produce();
         sender.send();
    }
}
