package com.meihaifeng.proxy.staticproxy;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/29
 * @description
 */
public class RealSubject implements Subject{
    public void request(){
        System.out.println("request");
    }
}
