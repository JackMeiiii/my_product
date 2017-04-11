package com.meihaifeng.factory_mode.proxy;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/3
 * @description
 */
public class ProxySource implements Sourceable {

    private Source source;

    public ProxySource(){
        super();
        this.source =new  Source();
    }

    @Override
    public void method() {
        before();
        source.method();
        after();
    }

    private void before(){
        System.out.println("before proxy!");
    }

    private void after(){
        System.out.println("after proxy!");
    }

    public static void main(String args[]) {

        ProxySource proxySource = new ProxySource();
        proxySource.method();

    }
}
