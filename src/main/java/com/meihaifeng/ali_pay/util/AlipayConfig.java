package com.meihaifeng.ali_pay.util;

/**
 * 杭州新苗网络科技有限公司
 *
 * @author meihf
 * @create 2017/5/10
 * @description
 */
public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID
    public static String alipay_appId = "2017060307414252";

    public static String aliPayPartner = "2017060307414252";


    // 收款支付宝账号，一般情况下收款账号就是签约账号
    public static String aliPaySellerEmail = "362444800@qq.com";

    // 商户的私钥
    public static String aliPayPrivate_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfAyfYeqIKOppPpgUjGR62BjhOF6fyAbjCyHfJDCZXsFIZvhEkNyq/DWC+idR45LNfoxxLFinlQRTXWuScz39MBZJmfodpRuc9FXK9p3es3H29vmfyrcvbIxrN6Cu6agEXUCkTwr4A408hvNeDJXO5U211xq5Cykz7xQQLQpVXAuohLvUj2iyocdFuAUO4lJrMSLEiAoLl9DRCEzgzIQb38sJOzbKROH0LwJm5u2Y1sKncxHmJJ4BtZFKL+/LzFvy+/Am81y2giMZCKBjy5ryr+MR5Cz1eOYk4/omX/Qm6mczKW697wODL8iroKx8lNIgVrp1EMapTLpW49nlihYCrAgMBAAECggEAUnPxcmhMo981av2chlGdQifBhITxuiS4aJBIxMB/FVNBj0WmX9AXY1ANjV+tBC8a8AmFP04z1UPFCdKA3usdiA8PlgQgNS/l8h+wCrXO8Zy3uu8DuavzHCN83653FkenXy5azntZyN4Vh1oDqmZ+NmgTR1IJxGpgNuE3MpuJEr+H1IvQnj1Djk5sQNNI5CcIL/DFuV/D5W/1w2MXJZzvysT0guRZm0LFWDk5ReAMQ1nqawAhhx6LfvnlkFpQykkBS7JdMEPeoC/qjMvyOqEF4H+BBYk0wPiJrce47D1OIs3mEEw7//0xSeYI5bW12xk2v5HFbOnKiC8Ni1dn66t4kQKBgQDPWIO+li+4g3UvtZkp+/gyVFRFyTp8bDtGpxX0G44qhUa0T3Sd6L3n+NemgwnfVNgyJPU2/ghZBK4QXQKAAnzon4Lkz7DjOOTsefUzDEYdgecVupEMUB9ghTEWXDdYnxbDcpqBPx+udkznzRwZ6zHAPTAaY132Dq9KrqT9EVIjuQKBgQDEUzL3nuzcNgbs5F3AiHlzmlJJBkglxjkO955OZlMsLmp2D9qtgG/pKMRXtJut+uLs6YwLgRB5hEm3Z8hzvcrIpIq2ZRPMKdVxkArzDKsHXerFEnSZjdM05WuqB7VusbNZBvtL/b+wV2Ie1dq0rP8sZIRZeIGBnVqtKDAPFeWBgwKBgHbuJ4svLCoixRnbiQIK9B8yPbBVrwVlM07gUfhVweLj1V1hYdudfyhKlK9+5C/Ew8lnZ3vmlDUzOQBLyT5RrwHjVLoX4zdCEBUQEzP3q1QLidftznsIjibX3wMbW+eqQO1UlAjkgzCXzJ79CExZmozQzAGm02NqouMsb5h00e+JAoGBAKGo1MYb4nRuNIOqhBx/BGsRvwIMnyojsGZHtd5e2cv+qIFxCqNlqEE45XxG5w57EZL9uOjcLQGy0bSpS+Eqfil7fBE25/ds1yo87xc17jsRtbjzOnoa07ix1VbcJCTzRHhhjFoFp2uAePZlKEMwVKpsVqMJ3LIrLTh6aCBz6lkbAoGAB96OMI8u8mvGDoXxWsJeAyk8gsR14MOQaJCESJEloIggfrcphlfKVnqX3X2bOtfV5vLTuNn4g0SbDtd401dfov8iwU/kh3Xo5VJXdpNcNYSPESsnkEEzpwtQPjsgEZbMDp95MzQ3YSCXL7kcyOanQzNc8AFqCk/holr6LJEyGdM=";



    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


    // 调试用，创建TXT日志文件夹路径
    public static String aliPayLogPath = "E:\\alipay\\log";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String aliPayCharset = "utf-8";

    // 签名方式 不需修改
    public static String aliPaySignType = "RSA";

    public static String aliPayType = "1";

    public static String aliPayService = "mobile.securitypay.pay";

    public static String aliPayNotifyUrl = "http://121.40.99.146:8084/yyyy/tradePay/notifyAli";

    // 支付宝公钥
    public static String aliPayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkK0nJHHVGJh652evTvjHD5hR++dPapoM6FS8ng1ssNsZEv0dyZD65AVGHk5H56VNW2hlCeX7Lsk64SOP0TWBzrj5Qwny1920i7on50HBlXyc+jM9CzIETrozKyaHJsF1zSUx/NtBioYuu8fDbZSwsb+P7PvcasLfT787DrmQYNlBw//kU6vI8JPt5x4u+SPy5cAJI2NLwNFGHGdUcs0513QqrlDqq54R2hY5X5f94ZY9aejdI/datdf/Rph/Y9NKjXtZA3Cnhw0z1zInAgqJXWK2TaLVCO0iKwu2ar7jNMwl+o/qBMtGeRsCfNfzjWzHxT9HjKW3F/+ZtAanPle8QQIDAQAB";

}
