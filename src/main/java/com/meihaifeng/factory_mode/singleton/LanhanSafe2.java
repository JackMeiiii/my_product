package com.meihaifeng.factory_mode.singleton;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/30
 * @description
 */
public class LanhanSafe2 {

    private volatile static  LanhanSafe2 lanhanSafe2= null;
    private LanhanSafe2(){

    }
    public static LanhanSafe2 getInstance(){
        if (lanhanSafe2==null){
            synchronized (LanhanSafe2.class){
                if (lanhanSafe2==null){
                    lanhanSafe2=new LanhanSafe2();
                }
            }
        }
        return lanhanSafe2;
    }
}
