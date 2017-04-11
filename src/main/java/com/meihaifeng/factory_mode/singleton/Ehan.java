package com.meihaifeng.factory_mode.singleton;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/29
 * @description
 */
public class Ehan {

    private Ehan(){

    }

    private static Ehan singleton = new Ehan();

    //静态工厂方法
    public static Ehan getInstance(){
        return singleton;
    }
}
