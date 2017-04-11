package com.meihaifeng.factory_mode.singleton;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/29
 * @description
 */
public class Lanhan {

    private Lanhan(){

    }
    private static Lanhan lanhan = null;

    public static Lanhan getInstance(){
        if (lanhan == null){
            lanhan = new Lanhan();
        }
        return lanhan;
    }
}
