package com.meihaifeng.weixin_pay.public_number.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xm.xm.dto.ResultDto;
import com.xm.xm.dto.SearchDto;
import com.xm.xm.util.BeanUtils;
import com.xm.xm.util.DateUtils;
import com.xm.xm.util.ErrorCode;
import com.xm.xm.util.HttpUtils;
import com.xm.xmds1705083.service.config.WxConfig;
import com.xm.xmds1705083.service.dto.DuoduoPaymentRecordsDto;
import com.xm.xmds1705083.service.mapper.DuoduoPaymentRecordsMapper;
import com.xm.xmds1705083.service.mapper.DuoduoWeixinUserMapper;
import com.xm.xmds1705083.service.model.DuoduoWeixinUser;
import com.xm.xmds1705083.service.service.WxService;
import com.xm.xmds1705083.service.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 杭州新苗网络科技有限公司
 *
 * @author meihf
 * @create 2017/6/26
 * @description
 */
@Service("wxService")
public class WxServiceImpl implements WxService {

    @Autowired
    @Qualifier("redisTemplate")
    public RedisTemplate<String,Integer> redisTemplate;

    @Autowired
    DuoduoWeixinUserMapper duoduoWeixinUserMapper;

    @Autowired
    DuoduoPaymentRecordsMapper duoduoPaymentRecordsMapper;

    protected Logger logger = LogManager.getLogger(getClass());

    @Override
    public ResultDto auth(SearchDto searchDto) {
        ResultDto resultDto = new ResultDto();
        searchDto.setPageNo((searchDto.getPageNo()-1)*searchDto.getPageSize());
        try {
             String url = WxConfig.AUTH_URL+"?appid="+WxConfig.APPID+"&redirect_uri="+WxConfig.REDIRECT_URL+"&response_type="+WxConfig.RESPONSE_TYPE+"&scope="
                     +WxConfig.SCOPE+"&state="+WxConfig.STATE+WxConfig.END;
             resultDto.setData(url);
        }catch (RuntimeException e ){
            e.printStackTrace();
            resultDto.setCode(ErrorCode.C10013);
            resultDto.setMsg(ErrorCode.E10013);
        }
        return resultDto;
    }

    @Override
    public ResultDto getUserInfo(String openid,String access_token,String refresh_token,String expires_in) {
        ResultDto resultDto = new ResultDto();
        try {
            //先查库中有没有这个openid
            int size = duoduoWeixinUserMapper.selectList(new EntityWrapper<DuoduoWeixinUser>().where(" openid={0} ",openid)).size();
            if (size==0){
                //获取openid，token之后再获取用户信息
                String info_url = WxConfig.GET_INFO_URL+"?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
                ResultDto infoResult = BeanUtils.copy(HttpUtils.doGet(info_url,null),ResultDto.class);
                //用户的个人信息
                JSONObject result_json_info = JSONObject.fromObject(String.valueOf(infoResult.getData()));
                String nickname = result_json_info.getString("nickname");
                String sex = result_json_info.getString("sex");
                String province = result_json_info.getString("province");
                String city = result_json_info.getString("city");
                String country = result_json_info.getString("country");
                String headimgurl = result_json_info.getString("headimgurl");
                JSONArray privilege = result_json_info.getJSONArray("privilege");
                String language = result_json_info.getString("language");
//            String unionid = result_json_info.getString("unionid");
                DuoduoWeixinUser duoduoWeixinUser = new DuoduoWeixinUser();
                duoduoWeixinUser.setCity(city);
                duoduoWeixinUser.setCountry(country);
                duoduoWeixinUser.setHeadimgurl(headimgurl);
                duoduoWeixinUser.setNickName(nickname);
                duoduoWeixinUser.setOpenid(openid);
                duoduoWeixinUser.setProvince(province);
                duoduoWeixinUser.setPrivilege(privilege.toString());
                //保存信息到数据库
                duoduoWeixinUserMapper.insert(duoduoWeixinUser);
            }
            resultDto.setMsg("保存成功");
        }catch (RuntimeException e ){
            e.printStackTrace();
            resultDto.setCode(ErrorCode.C10013);
            resultDto.setMsg(ErrorCode.E10013);
        }
        return resultDto;
    }

    @Override
    public ResultDto orderPay(SearchDto searchDto) {
        ResultDto resultDto = new ResultDto();
        JSONObject filed = searchDto.getFiled();
        searchDto.setPageNo((searchDto.getPageNo()-1)*searchDto.getPageSize());
        try {
            double totalFee = filed.getDouble("totalFee");
            String accountNo = filed.getString("accountNo");
            String ip = filed.getString("ip");
            String openid = filed.getString("openid");

            DuoduoPaymentRecordsDto duoduoPaymentRecordsDto = new DuoduoPaymentRecordsDto();
            duoduoPaymentRecordsDto.setOrderNo(accountNo);
            duoduoPaymentRecordsDto.setOrderName("支付");
            duoduoPaymentRecordsDto.setBody("订单支付");
            duoduoPaymentRecordsDto.setTotalFee(totalFee);
            duoduoPaymentRecordsDto.setExterInvokeIp(ip);
            duoduoPaymentRecordsDto.setOpenid(openid);
            return unifiedorder(duoduoPaymentRecordsDto);
        }catch (RuntimeException e ){
            e.printStackTrace();
            resultDto.setCode(ErrorCode.C10013);
            resultDto.setMsg(ErrorCode.E10013);
        }
        return resultDto;
    }

    /**
     * 微信统一支付
     * @param 
     * @return
     */
    public ResultDto unifiedorder(DuoduoPaymentRecordsDto duoduoPaymentRecordsDto){
        ResultDto resultDto = new ResultDto();
        try {
            BigDecimal totalFee = new BigDecimal(duoduoPaymentRecordsDto.getTotalFee()).multiply(new BigDecimal("100"));
            BigDecimal zero = new BigDecimal("0");
            if (totalFee.compareTo(zero) == -1) {
                totalFee = zero;
            }
            String appid = WxConfig.APPID;
            String mch_id = WxConfig.MCHID;
            String nonce_str = RandCharsUtils.getRandomString(32);
            String body = duoduoPaymentRecordsDto.getBody();
            int total_fee = totalFee.intValue();  //单位为分
            String detail = "";
            String attach = duoduoPaymentRecordsDto.getOrderName();
            String out_trade_no = duoduoPaymentRecordsDto.getOrderNo()+""+RandCharsUtils.timeStart();
            String spbill_create_ip = duoduoPaymentRecordsDto.getExterInvokeIp();
            String time_start = RandCharsUtils.timeStart();
            String time_expire = RandCharsUtils.timeExpire();
            String notify_url = WxConfig.NOTIFY_URL;
            //JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
            //MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
            String trade_type = "JSAPI";
            String openid = duoduoPaymentRecordsDto.getOpenid();

            //加签
            SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
            parameters.put("appid", appid);
            parameters.put("mch_id", mch_id);
            parameters.put("nonce_str", nonce_str);
            parameters.put("body", body);
//            parameters.put("nonce_str", nonce_str);
            parameters.put("detail", detail);
            parameters.put("attach", attach);
            parameters.put("out_trade_no", out_trade_no);
            parameters.put("total_fee", total_fee);
            parameters.put("time_start", time_start);
            parameters.put("time_expire", time_expire);
            parameters.put("notify_url", notify_url);
            parameters.put("trade_type", trade_type);
            parameters.put("spbill_create_ip", spbill_create_ip);
            parameters.put("openid",openid);

            String sign = WXSignUtils.createSign("UTF-8", parameters);
            System.out.println("生成签名"+sign);

            Unifiedorder unifiedorder = new Unifiedorder();
            unifiedorder.setAppid(appid);
            unifiedorder.setMch_id(mch_id);
            unifiedorder.setNonce_str(nonce_str);
            unifiedorder.setSign(sign);
            unifiedorder.setBody(body);
            unifiedorder.setDetail(detail);
            unifiedorder.setAttach(attach);
            unifiedorder.setOut_trade_no(out_trade_no);
            unifiedorder.setTotal_fee(total_fee);
            unifiedorder.setSpbill_create_ip(spbill_create_ip);
            unifiedorder.setTime_start(time_start);
            unifiedorder.setTime_expire(time_expire);
            unifiedorder.setNotify_url(notify_url);
            unifiedorder.setTrade_type(trade_type);
            unifiedorder.setOpenid(openid);

            //封装成xml
            String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
            String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
            String method = "POST";
            String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

            UnifiedorderResult result = JdomParseXmlUtils.getUnifiedorderResult(weixinPost);
            ParseXMLUtils.jdomParseXml(weixinPost);

            // 再次加签
            long timestamp = DateUtils.getTimeSecondLong();
            SortedMap<Object, Object> prepayParams = new TreeMap<Object, Object>();
            if (total_fee > 0 || (result.getReturn_code() != null && "SUCCESS".equals(result.getReturn_code()) && result.getResult_code() != null
                    && "SUCCESS".equals(result.getResult_code()))) {
                prepayParams.put("appId", appid);
                prepayParams.put("timeStamp", timestamp);
                prepayParams.put("nonceStr", nonce_str);
                prepayParams.put("package","prepay_id="+result.getPrepay_id());
                prepayParams.put("signType", "MD5");
            }
            String prepaySign = WXSignUtils.createSign("UTF-8", prepayParams);
            Map map = new HashMap();
            map.put("appId",appid);
            map.put("package","prepay_id="+result.getPrepay_id());
            map.put("nonceStr",nonce_str);
            map.put("timestamp", timestamp);
            map.put("signType","MD5");
            map.put("paySign",prepaySign);
            resultDto.setCode(0);
            resultDto.setMsg("获取微信参数成功");
            resultDto.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            resultDto.setCode(2000);
            resultDto.setMsg("获取微信参数失败");
        }

        return resultDto;
    }


    /**
     * 微信回调接口
     * @param
     * @param
     * @param
     * @param
     * @throws Exception
     */
    @Override
    public ResultDto weChatNotify(Map<String,Object> params) {
        ResultDto resultDto = new ResultDto();
        boolean flag = false;
        String result_code = String.valueOf(params.get("result_code"));
        System.out.println("微信支付结果" + result_code);
        String result_msg = String.valueOf(params.get("return_msg"));
        System.out.println("返回信息"+ result_msg);

        String attach = String.valueOf(params.get("attach"));       //支付
        String order = String.valueOf(params.get("out_trade_no"));  //out_trade_no=3520170523173329
        order = order.substring(0,order.length()-14);               //uid or takeCar
        double cash_fee = Double.parseDouble(params.get("cash_fee").toString())/100.00; //金额
        System.out.println(cash_fee);

        System.out.println(params);
        try {
            //反校验签名防第三方篡改
            flag = Signature.checkIsSignValidFromResponseString(params);
            if (flag == true) {
                if (result_code.equals("SUCCESS")) {
                    if (attach.equals("支付")){
                       //将缴费记录保存到表里面 或者可以新调接口
                    }
                    resultDto.setCode(0);
                    String xml = "<xml> <return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg> </xml>";//XML文本字符串
                    resultDto.setData(xml);
                }
                //失败时候字段信息
            }else if (result_code.equals("FAIL")){
                String error_code = String.valueOf(params.get("err_code"));
                String error_code_msg = String.valueOf(params.get("err_code_des"));
                resultDto.setMsg("错误码"+error_code+"错误详情"+error_code_msg);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return resultDto;
    }



}
