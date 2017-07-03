package com.meihaifeng.weixin_pay.public_number.service;

import com.xm.xm.dto.ResultDto;
import com.xm.xm.dto.SearchDto;

import java.util.Map;

/**
 * 杭州新苗网络科技有限公司
 *
 * @author meihf
 * @create 2017/6/26
 * @description
 */
public interface WxService {
    ResultDto auth(SearchDto searchDto);

    ResultDto orderPay(SearchDto searchDto);

    ResultDto getUserInfo(String openid, String access_token, String refresh_token, String expires_in);

    ResultDto weChatNotify(Map<String, Object> params);
}
