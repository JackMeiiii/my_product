package com.meihaifeng.thread;

import java.util.concurrent.*;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/22
 * @description
 */
public class SetPriority implements Runnable{

    private int countDown = 5;
    private volatile double d;
    private int pri;
    public SetPriority(int pri){
        this.pri = pri;
    }
    public String toString(){
        return Thread.currentThread() +" : "+ countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(pri);
        while (true){
            for (int i=1;i<100000;i++){
                d += (Math.PI + Math.E)/(double)i;
                if (i % 1000 == 0){
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<5;i++){
            executorService.execute(new SetPriority(Thread.MIN_PRIORITY));
        }
        executorService.execute(new SetPriority(Thread.MAX_PRIORITY));
        executorService.shutdown();
    }
}
