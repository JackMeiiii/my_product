package com.meihaifeng.common;

import com.xm.xm.config.MsgConfig;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 杭州新苗网络科技有限公司
 *
 * @author meihf
 * @create 2017/5/23
 * @description
 */
public class SendMsgUtil {

    public SendMsgUtil(){}

    public static String notice(Integer validateCode,String orderNo,String shopName){
        return MsgConfig.yyyy_sign+" 您的快递号："+orderNo+"已到达"+shopName+", 请凭验证码："+validateCode+"取货";
    }

    public static ResultDto send(String uri, String account, String pswd, String mobiles, String content, boolean needstatus, String product, String extno) throws Exception {
        ResultDto resultDto = new ResultDto();
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod();

        try {
            URI base = new URI(uri, false);
            method.setURI(new URI(base, "HttpSendSM", false));
            method.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
            method.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            method.setRequestBody(new NameValuePair[]{new NameValuePair("account", account), new NameValuePair("pswd", pswd), new NameValuePair("mobile", mobiles), new NameValuePair("needstatus", String.valueOf(needstatus)), new NameValuePair("msg", URLEncoder.encode(content, "UTF-8")), new NameValuePair("product", product), new NameValuePair("extno", extno)});
            int result = client.executeMethod(method);
            if(result != 200) {
                resultDto.setCode(method.getStatusCode());
                resultDto.setMsg(method.getStatusText());
                return resultDto;
            } else {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                boolean len = false;

                int len1;
                while((len1 = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len1);
                }

                String var17 = URLDecoder.decode(baos.toString(), "UTF-8");
                String[] results = var17.split(",");
                int code = Integer.parseInt(results[1].substring(0,1));
                if (code!=0){
                    resultDto.setCode(code);
                    resultDto.setMsg("发送短信失败");
                }else {
                    resultDto.setCode(code);
                    resultDto.setMsg("发送短信成功");
                }
                return resultDto;
            }
        } finally {
            method.releaseConnection();
        }
    }

    //群发功能暂时没有用到
    public static String batchSend(String uri, String account, String pswd, String mobiles, String content, boolean needstatus, String product, String extno) throws Exception {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod();

        try {
            URI base = new URI(uri, false);
            method.setURI(new URI(base, "HttpBatchSendSM", false));
            method.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
            method.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            method.setRequestBody(new NameValuePair[]{new NameValuePair("account", account), new NameValuePair("pswd", pswd), new NameValuePair("mobile", mobiles), new NameValuePair("needstatus", String.valueOf(needstatus)), new NameValuePair("msg", URLEncoder.encode(content, "UTF-8")), new NameValuePair("product", product), new NameValuePair("extno", extno)});
            int result = client.executeMethod(method);
            if (result != 200) {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            } else {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                boolean len = false;

                int len1;
                while ((len1 = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len1);
                }

                String var17 = URLDecoder.decode(baos.toString(), "UTF-8");
                return var17;
            }
        } finally {
            method.releaseConnection();
        }
    }
}
