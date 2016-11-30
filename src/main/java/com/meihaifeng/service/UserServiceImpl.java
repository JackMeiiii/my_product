package com.meihaifeng.service;import com.meihaifeng.dao.impl.UserDaoImpl;import com.meihaifeng.entity.RestMessage;import com.meihaifeng.entity.User;import com.meihaifeng.util.RedisCacheUtil;import org.apache.commons.httpclient.util.DateUtil;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.Date;import java.util.HashMap;import java.util.Map;/** * 浙江卓锐科技股份有限公司 * * @author meihf * @date 2016/11/10 * @description */@Servicepublic class UserServiceImpl {    @Autowired    UserDaoImpl userDao;    @Autowired    RedisCacheUtil<Object> redisCacheUtil;    public Map<String,Object> getUserLog(int index){        User user = new User();        user.setId(String.valueOf(index));        Map<String,Object> map = new HashMap<String,Object>();        map.put("12","123");        return map;    }    public RestMessage getUserLog2(){        RestMessage restMessage = new RestMessage();        Map<String,Object> map = new HashMap<>();        map.put("msg","aop annotion 演示！");        restMessage.setMessage(map);        restMessage.setSuccess(true);        return restMessage;    }    public User getUserLog3(){        User user = new User();        user.setId("success!");        user.setName("meihf");        return user;    }    public User getUserLog4(){        User info = new User();        info.setId("9429");        info.setName("meihf");        Map<String,Object> map = new HashMap<String,Object>();        map.put("meihf","SUCCESS!");        return info;    }    public void delete() throws Exception {        throw new Exception("throw 演示!");    }    public User getUserLog5(){        String time = DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");        User user = new User();        user.setId(time);        user.setName("redis 缓存测试！");        userDao.saveUser(user);        return userDao.getUser(time);    }    public Map<Integer,User> getRedisCache() {        Map<Integer,User> userMap =  redisCacheUtil.getCacheIntegerMap("user");        return userMap;    }}