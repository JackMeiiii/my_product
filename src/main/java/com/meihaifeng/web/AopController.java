package com.meihaifeng.web;import com.alibaba.fastjson.JSONObject;import com.meihaifeng.entity.ResponseMessage;import com.meihaifeng.entity.User;import com.meihaifeng.service.aspect.UserServiceImpl;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseBody;import java.util.HashMap;import java.util.Map;/** * 浙江卓锐科技股份有限公司 * * @author meihf * @date 2016/11/10 * @description */@Controller@RequestMapping("aop")public class AopController {    @Autowired    UserServiceImpl userService;    @ResponseBody        @RequestMapping("/getUserLog")    public Map<String,Object> getUserLog(){        Map<String,Object> map = userService.getUserLog(1);        return map;    }    //返回值为User    @ResponseBody        @RequestMapping("/getUserLog2")    public User getUserLog2(){        Map<String,Object> map = userService.getUserLog2(1);        User user = new User();        user.setId((String) map.get("id"));        return user;    }    //参数为JSON    @ResponseBody        @RequestMapping("/getUserLog3")    public Map<String,Object> getUserLog3(JSONObject user){        Map<String,Object> map = userService.getUserLog3(Integer.parseInt((String) user.get("id")));        return map;    }}