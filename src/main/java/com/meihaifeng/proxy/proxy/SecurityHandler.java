package com.meihaifeng.proxy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/3/29
 * @description
 */
public class SecurityHandler implements InvocationHandler{
    //使用一个通用的对象
    private Object targetObject;

    public Object createProxyInstance(Object object){
        this.targetObject = object;
        //根据目标接口生成代理
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),
                this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(targetObject,args);
        return result;
    }
}
