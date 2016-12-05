package com.meihaifeng.activemq;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2016/12/5
 * @description
 */
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
public class Receiver {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext-*.xml");

        JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
        while(true) {
            Map<String, Object> map =  (Map<String, Object>) jmsTemplate.receiveAndConvert();

            System.out.println("收到消息：" + map.get("message"));
        }
    }
}
