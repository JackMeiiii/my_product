package com.meihaifeng.weixin_pay.util;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 杭州新苗网络科技有限公司
 *
 * @author meihf
 * @create 2017/5/9
 * @description
 */
public class TradePayDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /*支付类型必填 1.微信 2.支付宝*/
    private Integer payType;
    /*商品订单号必填*/
    private Long orderNo;
    /*订单标题名称必填*/
    private String orderName;
    /*付款金额必填*/
    private BigDecimal totalFee;

    /*商品描述*/
    private String body;

    /*商品详情*/
    private String orderDetail;

    /*异步通知地址*/
    private String notifyUrl;

    private String showUrl;

    private String antiPhishingKey;

    private String exterInvokeIp;

    private String timeOut;

    private Long id;

    private String feeType;

    private String startTime;

    private String expireTime;

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public String getAntiPhishingKey() {
        return antiPhishingKey;
    }

    public void setAntiPhishingKey(String antiPhishingKey) {
        this.antiPhishingKey = antiPhishingKey;
    }

    public String getExterInvokeIp() {
        return exterInvokeIp;
    }

    public void setExterInvokeIp(String exterInvokeIp) {
        this.exterInvokeIp = exterInvokeIp;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
