package com.meihaifeng.weixin_pay.util;

/**
 * 杭州新苗网络科技有限公司
 *
 * @author meihf
 * @create 2017/6/26
 * @description
 */
public class WxConfig {

    public static  String APPID = "wxe7ca41cea0e5a8de";
    public static  String MCHID = "1483359852";
    public static  String APPSECRET = "c9b2fc643cdaa6da964f75d8bdab1dbb";
    public static  String APPKEY = "7919f3dc589b4aa6a3fa121b33b20e84";
    public static  String REDIRECT_URL = "http://xmds1705083.java.hzxmnet.com/hppark/weixin/getOpenId";//授权后重定向的回调链接地址，请使用urlencode对链接进行处理
    public static  String NOTIFY_URL = "http://xmds1705083.java.hzxmnet.com/hppark/weixin/weChatNotify";//回调函数地址
    public static  String RESPONSE_TYPE = "code";//返回类型，请填写code
    public static  String SCOPE = "snsapi_userinfo";//应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
    public static  String STATE = "iZ11558begmZ";//重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
    public static  String END = "#WECHAT_REDIRECT";//无论直接打开还是做页面302重定向时候，必须带此参数

    // 发起授权地址
    public static  String AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
    // 获取token地址
    public static  String TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    // 获取用户信息地址
    public static  String GET_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    //重定向地址
    public static  String REDIRECT_LOGIN_URL = "http://xmds1705083.h5.hzxmnet.com/login.html";

    public static String OPENID = "";

    public static String CODE = "";

    public static String ACCESS_TOKEN = "";

    public static String REFRESH_TOKEN = "";

    public static String EXPIRES_IN = "";





}
