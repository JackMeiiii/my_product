package com.meihaifeng.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Loysen on 2017/3/1.
 * 短信接口部分
 */
public class SmsUtils {

    static HttpClient httpclient;
    static HttpPost httpPost_vaild_code;

    static {
        // 创建HttpClient实例
        httpclient = new DefaultHttpClient();
        // 创建Get方法实例
        httpPost_vaild_code = new HttpPost("http://sms.chengweigg.com/sms.aspx");
    }


    /**
     *
     * @param content
     * @return	returnstatus 	是否成功
    message 		返回消息
    remainpoint  	余额
    taskID			消息ID
    successCounts	成功短信数：当成功后返回提交成功短信数
     * @throws ClientProtocolException
     * @throws IOException
     * @throws DocumentException
     */
    @SuppressWarnings({ "deprecation", "unused", "rawtypes", "unchecked" })
    public static Map sendValidCode(String phone, String content) throws  IOException, DocumentException{
        // 创建参数队列
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "send"));
        params.add(new BasicNameValuePair("userid", "3414"));
        params.add(new BasicNameValuePair("account", "他山网络验证码"));
        params.add(new BasicNameValuePair("password", "147258"));
        params.add(new BasicNameValuePair("mobile", phone));
        params.add(new BasicNameValuePair("content", content));
        params.add(new BasicNameValuePair("sendTime", ""));
        params.add(new BasicNameValuePair("extno", ""));
        httpPost_vaild_code.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        HttpResponse response = httpclient.execute(httpPost_vaild_code);
        HttpEntity entity = response.getEntity();
        Map map = new HashMap();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            //解析返回结果 XML
            SAXReader saxReader = new SAXReader();
            Document document = DocumentHelper.parseText(result);
            Element root = document.getRootElement();
            Element returnstatus = (Element)root.elements("returnstatus").get(0);
            Element message = (Element)root.elements("message").get(0);
            Element remainpoint = (Element)root.elements("remainpoint").get(0);
            Element taskID = (Element)root.elements("taskID").get(0);
            Element successCounts = (Element)root.elements("successCounts").get(0);

            map.put("returnstatus", returnstatus.getText());
            map.put("message", message.getText());
            map.put("remainpoint", remainpoint.getText());
            map.put("taskID", taskID.getText());
            map.put("successCounts", successCounts.getText());
            httpPost_vaild_code.abort();
        }
        return map;
    }


    public static  String validateCode(Integer validateCode){
        return  "您的短信验证码是："+validateCode+"。10分钟内有效,平台不会以任何方式向您索要密码，请勿告知他人。【营销大师】";
    }

    public static String notice(Integer validateCode,String orderNo,String shopName){
        return "您的快递号："+orderNo+"已到达"+shopName+", 请凭验证码："+validateCode+"取货";
    }

    /**
     * 1分钟毫秒数
     */
    public static Integer  oneMinute(){
        return  1*60;
    }

    /**
     * 1分钟毫秒数
     */
    public static Integer  fiveMinute(){
        return  5*60;
    }

    /**
     * 15分钟毫秒数
     */
    public static Integer  fifteenMinute(){
        return  15*60;
    }

    public static Integer  tenMinute(){
        return  10*60;
    }

    public static void main(String[] args) throws Exception {
        SmsUtils s = new SmsUtils();
        s.sendValidCode("17310141320","123456");
    }


}
