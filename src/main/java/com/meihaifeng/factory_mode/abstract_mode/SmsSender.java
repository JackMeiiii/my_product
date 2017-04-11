package com.meihaifeng.factory_mode.abstract_mode;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/2/7
 * @description
 */
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is sms sender!");
    }
}
