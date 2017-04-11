package com.meihaifeng.factory_mode.singleton;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/29
 * @description
 */
public class LanhanSafe1 {

    private static LanhanSafe1 lanhanSafe1 = null;
    private LanhanSafe1(){

    }

    public static synchronized LanhanSafe1 getInstance(){
        if (lanhanSafe1 == null){
            lanhanSafe1 = new LanhanSafe1();
        }
        return lanhanSafe1;
    }

}
