package com.meihaifeng.test;

import com.meihaifeng.dao.UserDao;
import com.meihaifeng.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @date 2016/8/18
 * @description
 */
public class Test {
//    public static void redis(String[] args) {
//        ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:/data-source.xml");
//        RedisClientTemplate redisClient = (RedisClientTemplate)ac.getBean("redisClientTemplate");
//        redisClient.set("ab", "abc");
//        System.out.println(redisClient.get("a"));
//    }

    public static void main(String[] args) {
        ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        UserDao userDAO = (UserDao)ac.getBean("userDAO");
        User user1 = new User();
        user1.setId("3");
        user1.setName("obama3");
        userDAO.saveUser(user1);
        User user2 = userDAO.getUser("2");
        System.out.println(user2.getName());
    }

//    public static void redis(String[] args) {
//        System.out.println("return value of getValue(): " + getValue());
//    }
//
//    public static int getValue() {
//        int i = 1;
//        try {
//             i=1;
//        } finally {
//            return i++;
//        }
//    }
}
