package com.github.liujiebang.pay.ali.config;


import java.io.Serializable;

public class AliConfig implements Serializable {

    private String body = "深圳维码物联";

    private String subject = "订单结算";

    private String timeoutexpress = "10m";

    private String signType = "RSA2";

    private String charset = "utf-8";

    private String object = "json";

    private String gateway = "https://openapi.alipay.com/gateway.do";


    public static class payType {
        public static final String ALI_WEB = "ALI_WEB";
        public static final String ALI_APP = "ALI_APP";
        public static final String ALI_WAP = "ALI_WAP";
        public static final String ALI_SP = "ALI_SP";
        public static final String ALI_QR = "ALI_QR";
    }

    public static class Product {
        public static final String PRODUCTCODE_APP = "QUICK_MSECURITY_PAY";
        public static final String PRODUCTCODE_WEB = "FAST_INSTANT_TRADE_PAY";
        public static final String PRODUCTCODE_WAP = "QUICK_WAP_WAY";
        public static final String PRODUCTCODE_AUTH_ONLINE = "PRE_AUTH_ONLINE";
    }

    public static class PayeeType {
        public static final String ALIPAY_LOGONID = "ALIPAY_LOGONID";
        public static final String ALIPAY_USERID = "ALIPAY_USERID";
    }

    public static class PRE_AUTH {
        public static final String COMPLETE = "COMPLETE";
        public static final String NOT_COMPLETE = "NOT_COMPLETE";
    }

    public static class PayStatus {
        /**
         * 交易创建，等待买家付款
         */
        public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        /**
         * 未付款交易超时关闭，或支付完成后全额退款
         */
        public static final String TRADE_CLOSED = "TRADE_CLOSED";
        /**
         * 交易支付成功
         */
        public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
        /**
         * 交易结束，不可退款
         */
        public static final String TRADE_FINISHED = "TRADE_FINISHED";
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTimeoutexpress() {
        return timeoutexpress;
    }

    public void setTimeoutexpress(String timeoutexpress) {
        this.timeoutexpress = timeoutexpress;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    @Override
    public String toString() {
        return "AliConfig{" +
                "body='" + body + '\'' +
                ", subject='" + subject + '\'' +
                ", timeoutexpress='" + timeoutexpress + '\'' +
                ", signType='" + signType + '\'' +
                ", charset='" + charset + '\'' +
                ", object='" + object + '\'' +
                ", gateway='" + gateway + '\'' +
                '}';
    }
}
