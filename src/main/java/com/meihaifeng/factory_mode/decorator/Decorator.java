package com.meihaifeng.factory_mode.decorator;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/2/8
 * @description
 */
public class Decorator implements Sourceable{

    private Sourceable sourceable;

    public Decorator(Sourceable sourceable){
        super();
        this.sourceable = sourceable;
    }


    @Override
    public void method() {
        sourceable.method();
    }
}
