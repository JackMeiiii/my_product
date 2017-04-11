package com.meihaifeng.factory_mode.Adapter;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/2/8
 * @description 类适配器
 */
public class Adaper extends Source implements Targetable{
    @Override
    public void method2() {
        System.out.println("this is the targetable!");
    }

    public static void main(String args[]) {
        Targetable target = new Adaper();
        target.method1();
        target.method2();
    }
}
