package com.meihaifeng.listener;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2016/11/30
 * @description
 */

import com.meihaifeng.entity.User;
import com.meihaifeng.service.impl.UserServiceImpl;
import com.meihaifeng.util.RedisCacheUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
* 监听器，用于项目启动的时候初始化信息
*/
@Component
public class RedisCacheListener implements ApplicationListener<ContextRefreshedEvent> {
    //日志
    private final Log log = LogFactory.getLog(RedisCacheListener.class);

    @Autowired
    private RedisCacheUtil<Object> redisCache;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //spring 启动的时候缓存信息
        //event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext"
        if (event.getApplicationContext().getParent()==null) {
            System.out.println("\n\n\n_________\n\n缓存数据 \n\n ________\n\n\n\n");
            User user = userService.getUserLog5();

            Map<Integer, Object> userMap = new HashMap<Integer, Object>();
            userMap.put(Integer.valueOf(1),user);

            redisCache.setCacheIntegerMap("user", userMap);
        }
    }

}
