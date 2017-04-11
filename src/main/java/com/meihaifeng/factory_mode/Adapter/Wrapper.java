package com.meihaifeng.factory_mode.Adapter;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/2/8
 * @description 对象适配器
 */
public class Wrapper implements Targetable{

    private Source source;

    public Wrapper(Source source){
        super();
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
