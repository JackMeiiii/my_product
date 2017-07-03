package com.meihaifeng.ali_pay.impl;



import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.meihaifeng.ali_pay.TradePayService;
import com.meihaifeng.ali_pay.util.AlipayConfig;
import com.meihaifeng.ali_pay.util.RandCharsUtils;
import com.meihaifeng.common.DateUtils;
import com.meihaifeng.common.ResultDto;
import com.meihaifeng.weixin_pay.util.*;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 *
 * @create X 2017/5/9
 * @description
 */
@Service(value = "tradePayService")
public class TradePayServiceImpl implements TradePayService {

    protected Logger logger = (Logger) LogFactory.getLog(TradePayServiceImpl.class);

    /**
     * 支付宝统一支付
     * @param tradePayDto
     * @return
     */
    @Override
    public ResultDto createAli(TradePayDto tradePayDto) {
        ResultDto resultDto = new ResultDto();

        //支付宝最小要精确到分，也就是0.01
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.00");
        String total_fee =  df.format(tradePayDto.getTotalFee());

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.alipay_appId,
                AlipayConfig.aliPayPrivate_key , "json", AlipayConfig.aliPayCharset, AlipayConfig.aliPayPublicKey, "RSA2");

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取b
        //
        // iz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setPassbackParams(URLEncoder.encode(tradePayDto.getOrderDetail()));  //描述信息  添加附加数据
        model.setSubject( tradePayDto.getOrderName()); //商品标题
        model.setOutTradeNo(String.valueOf(tradePayDto.getOrderNo()+""+ RandCharsUtils.timeStart())); //商家订单编号
        model.setTimeoutExpress("30m"); //超时关闭该订单时间
        model.setTotalAmount(total_fee);  //订单总金额
        model.setProductCode("QUICK_MSECURITY_PAY"); //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY

        System.out.println(model);

        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.aliPayNotifyUrl);  //回调地址
        String orderStr = "";
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            orderStr = response.getBody();
            System.out.println(orderStr);//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        resultDto.setData(orderStr);
        resultDto.setMsg("生成成功");
        return resultDto;

    }


    @Override
    public String notifyAli(Map<String,String> params) {
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        try {
            System.out.println("params ===" + params);
            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.aliPayPublicKey, AlipayConfig.aliPayCharset, "RSA2");

            System.out.println(flag);
            System.out.println(params.get("trade_status"));

            if (flag) {
                if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
                    //商户订单号
                    String order = params.get("out_trade_no");
                    order = order.substring(0, order.length() - 14);               //uid or takeCar
                    //付款金额
                    BigDecimal cash_fee = new BigDecimal(params.get("total_amount"));
                    System.out.println(cash_fee);
                    //充值  支付
                    String subject = String.valueOf(params.get("subject"));
                    System.out.println(subject);
                    /**------------
                     *  后台操作
                     --------------*/
                    if (subject.equals("支付")) {
//                    CloShopOrder cloShopOrder = cloShopOrderMapper.selectById(order);
//                    cloShopOrder.setPayStatus(1);
//                    cloShopOrder.setStatus(1);                                           //1待发货(已支付)
//                    cloShopOrder.setUpdateTime(DateUtils.getTimeSecondLong());
//                    cloShopOrderMapper.updateSelectiveById(cloShopOrder);
                }
                }
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        System.out.println("success");
        return "success";
    }


    /**
     * 微信统一支付
     * @param tradePayDto
     * @return
     */
    @Override
    public ResultDto unifiedorder(TradePayDto tradePayDto){
        ResultDto resultDto = new ResultDto();
        try {
            BigDecimal totalFee = tradePayDto.getTotalFee().multiply(new BigDecimal("100"));
            BigDecimal zero = new BigDecimal("0");
            if (totalFee.compareTo(zero) == -1) {
                totalFee = zero;
            }
//            logger.info("打印成功");

            String appid = "wxc39aebb5ae0d50e7";
            String mch_id = "1320324801";
            String nonce_str = RandCharsUtils.getRandomString(32);
            String body = tradePayDto.getBody();
            int total_fee = totalFee.intValue();  //单位为分
            String detail = "";
            String attach = tradePayDto.getOrderName();
            String out_trade_no = tradePayDto.getOrderNo()+""+RandCharsUtils.timeStart();
            String spbill_create_ip = tradePayDto.getExterInvokeIp();
            String time_start = RandCharsUtils.timeStart();
            String time_expire = RandCharsUtils.timeExpire();
            String notify_url = "http://121.40.99.146:8084/yyyy/tradePay/notifyWx";
            String trade_type = "APP";

            //加签
            SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
            parameters.put("appid", appid);
            parameters.put("mch_id", mch_id);
            parameters.put("nonce_str", nonce_str);
            parameters.put("body", body);
            parameters.put("nonce_str", nonce_str);
            parameters.put("detail", detail);
            parameters.put("attach", attach);
            parameters.put("out_trade_no", out_trade_no);
            parameters.put("total_fee", total_fee);
            parameters.put("time_start", time_start);
            parameters.put("time_expire", time_expire);
            parameters.put("notify_url", notify_url);
            parameters.put("trade_type", trade_type);
            parameters.put("spbill_create_ip", spbill_create_ip);

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

            //封装成xml
            String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
            String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
            String method = "POST";
            String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
            System.out.println(weixinPost);

            UnifiedorderResult result = JdomParseXmlUtils.getUnifiedorderResult(weixinPost);
            ParseXMLUtils.jdomParseXml(weixinPost);

            // 再次加签
            SortedMap<Object, Object> prepayParams = new TreeMap<Object, Object>();
            if (total_fee > 0 || (result.getReturn_code() != null && "SUCCESS".equals(result.getReturn_code()) && result.getResult_code() != null
                    && "SUCCESS".equals(result.getResult_code()))) {
                prepayParams.put("appid", appid);
                prepayParams.put("partnerid", mch_id);
                prepayParams.put("prepayid", result.getPrepay_id());
                prepayParams.put("noncestr", nonce_str);
                prepayParams.put("timestamp", DateUtils.getTimeSecondLong());
                prepayParams.put("package", "Sign=WXPay");
            }
            String prepaySign = WXSignUtils.createSign("UTF-8", prepayParams);
            Map map = new HashMap();
            map.put("partnerId",mch_id);
            map.put("prepayId",result.getPrepay_id());
            map.put("package","Sign=WXPay");
            map.put("nonceStr",nonce_str);
            map.put("timeStamp", DateUtils.getTimeSecondLong());
            map.put("sign",prepaySign);
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
//                        CloShopOrder cloShopOrder = cloShopOrderMapper.selectById(order);
//                        cloShopOrder.setPayStatus(1);
//                        cloShopOrder.setStatus(1);                                           //1待发货(已支付)
//                        cloShopOrder.setUpdateTime(DateUtils.getTimeSecondLong());
//                        cloShopOrderMapper.updateSelectiveById(cloShopOrder);
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

    /**
     * 微信支付宝联合支付
     * @param searchDto
     * @return
     */
    @Override
    public ResultDto orderPay(SearchDto searchDto) {
        int type = searchDto.getFiled().getInt("type");
        try {
//            Long orderId = searchDto.getFiled().getLong("orderId");
//            CloShopOrder cloShopOrder = cloShopOrderService.selectById(orderId);
//            cloShopOrder.setPayType(type);
//            cloShopOrderService.updateSelectiveById(cloShopOrder);
//            Double totalMoney = cloShopOrder.getTotalMoney();   //应付金额

            //1.支付宝
            if (type == 1){
//                TradePayDto tradePayDto = new TradePayDto();
//                tradePayDto.setOrderNo(orderId);
//                tradePayDto.setTotalFee(new BigDecimal(0.01));  //totalMoney
//                tradePayDto.setOrderName("支付");
//                tradePayDto.setOrderDetail("订单支付");
//                tradePayDto.setPayType(2);
//                return tradePayService.createAli(tradePayDto);
            }

            //1.微信
            if (type == 2){
//                TradePayDto tradePayDto = new TradePayDto();
//                tradePayDto.setOrderNo(orderId);
//                tradePayDto.setTotalFee(new BigDecimal(0.01));  //totalMoney
//                tradePayDto.setOrderName("支付");
//                tradePayDto.setBody("订单支付");
//                tradePayDto.setPayType(1);
//                tradePayDto.setExterInvokeIp(searchDto.getFiled().getString("ip"));
//                return tradePayService.unifiedorder(tradePayDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
