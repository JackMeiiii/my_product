package com.meihaifeng.test;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @date 2016/11/4
 * @description
 */
public class ThreadTest extends Thread{

    private  String name;
    public ThreadTest(String name){
        this.name=name;
    }

    @Override
    public void run(){
        for (int i=0;i<5;i++){
            System.out.println(name+"运行"+i);
            try {
                sleep((int)Math.random()*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        ThreadTest m1 = new ThreadTest("A");
        ThreadTest m2 = new ThreadTest("B");
        m1.start();
        m2.start();
        }
}
