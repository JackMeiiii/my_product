package com.meihaifeng.factory_mode.singleton;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/30
 * @description
 */
public class LanhanSafe3 {

    private static class InnerLanhan{
        private static final LanhanSafe3 INSTANCE = new LanhanSafe3();
    }
    private LanhanSafe3(){}

    public static final LanhanSafe3 getInstance(){
        return InnerLanhan.INSTANCE;
    }
}
