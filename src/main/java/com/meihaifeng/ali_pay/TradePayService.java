package com.meihaifeng.ali_pay;

import com.meihaifeng.common.ResultDto;
import com.meihaifeng.weixin_pay.util.SearchDto;
import com.meihaifeng.weixin_pay.util.TradePayDto;

import java.util.Map;

/**
 * 杭州新苗网络科技有限公司
 *
 * @author x
 * @create 2017/5/9
 * @description
 */
public interface TradePayService {

    /** 支付宝统一支付接口 */
    ResultDto createAli(TradePayDto tradePayDto);

    /** 支付宝回调接口 */
    String  notifyAli(Map<String, String> params);

    /** 微信回调接口 */
    ResultDto weChatNotify(Map<String, Object> param);

    /** 微信统一支付接口 */
    ResultDto unifiedorder(TradePayDto tradePayDto);

    /**微信&支付宝支付*/
    ResultDto orderPay(SearchDto searchDto);

}
