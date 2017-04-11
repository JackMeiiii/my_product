package com.meihaifeng.proxy.staticproxy;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/29
 * @description
 */
public class Proxy implements Subject{
    private Subject subject;
    public Proxy(Subject subject){
        this.subject = subject;
    }
    public void request(){
        System.out.println("PreProcess");
        subject.request();
        System.out.println("PostProcess");
    }
}
