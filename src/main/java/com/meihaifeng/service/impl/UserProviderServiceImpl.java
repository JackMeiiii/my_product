package com.meihaifeng.service.impl;

import com.meihaifeng.service.UserProviderService;
import org.springframework.stereotype.Service;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2016/12/27
 * @description
 */
@Service
public class UserProviderServiceImpl implements UserProviderService{


    @Override
    public void sayHello() {
        System.out.println("HELLO WORLD!");
    }
}
