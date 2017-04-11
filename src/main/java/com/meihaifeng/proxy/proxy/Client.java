package com.meihaifeng.proxy.proxy;

import com.meihaifeng.proxy.staticproxy.RealSubject;
import com.meihaifeng.proxy.staticproxy.Subject;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/29
 * @description
 */
public class Client {
    public static void main(String args[]) {
        SecurityHandler securityHandler = new SecurityHandler();
        Subject subject = (Subject) securityHandler.createProxyInstance(new RealSubject());
        Subject subject1 = new RealSubject();
        subject1.request();
        subject.request();
    }
}
