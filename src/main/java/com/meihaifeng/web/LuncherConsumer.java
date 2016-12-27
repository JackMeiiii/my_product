package com.meihaifeng.web;

import com.meihaifeng.service.UserProviderService;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2016/12/27
 * @description
 */
public class LuncherConsumer {

    public static void main(String args[]) throws InterruptedException {
       LuncherConsumer luncherConsumer = new LuncherConsumer();
        luncherConsumer.start();
        Thread.sleep(1000*60*10);
    }

    void start(){
        String configLocation = "classpath*:dubbo-consumer.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        String[] names = context.getBeanDefinitionNames();
        UserProviderService userProviderService = (UserProviderService) context.getBean("userService");
        System.out.println("Beans:");
        for (String name:names){
            System.out.println(name+",");
        }
        userProviderService.sayHello();
    }
}
