package com.github.liujiebang.pay.wx.entity;


public class WxRequest {

    public static final String WX_PP_PAY = "WX_PP";
    public static final String WX_SP_PAY = "WX_SP";
    public static final String WX_APP_PAY = "WX_APP";
    public static final String WX_NATIVE_PAY = "WX_NATIVE";
    public static final String WX_H5_PAY = "WX_H5";

    public static class tradeType {
        public static final String JSAPI = "JSAPI";
        public static final String NATIVE = "NATIVE";
        public static final String APP = "APP";
        public static final String MWEB = "MWEB";
    }

    public static class unifiedOrder {

        /**
         * 统一下单参数
         */
        public static final String APPID = "appid";

        public static final String MCH_ID = "mch_id";

        public static final String NONCE_STR = "nonce_str";

        public static final String SIGN = "sign";

        public static final String BODY = "body";

        public static final String OUT_TRADE_NO = "out_trade_no";

        public static final String TOTAL_FEE = "total_fee";

        public static final String SPBILL_CREATE_IP = "spbill_create_ip";

        public static final String NOTIFY_URL = "notify_url";

        public static final String TRADE_TYPE = "trade_type";
        /**
         * JSAPI模式必传
         */
        public static final String OPENID = "openid";
        /**
         * NATIVE模式必传
         */
        public static final String PRODUCT_ID = "product_id";
        /**
         * 微信返回预支付id
         */
        public static final String PREPAY_ID = "prepay_id";

    }

    public static class evokePaymentJSAPI {
        public static final String APPID = "appId";
        public static final String TIMESTAMP = "timeStamp";
        public static final String NONCESTR = "nonceStr";
        public static final String PACKAGE = "package";
        public static final String SIGNTYPE = "signType";
        public static final String PAYSIGN = "paySign";
    }

    public static class evokePaymentAPP {
        public static final String APPID = "appid";
        public static final String PARTNERID = "partnerid";
        public static final String PREPAYID = "prepayid";
        public static final String PACKAGE = "package";
        public static final String NONCESTR = "noncestr";
        public static final String TIMESTAMP = "timestamp";
        public static final String SIGN = "sign";
    }


    public static class evokePaymentNATIVE {
        public static final String CODE_URL = "code_url";
    }

    public static class evokePaymentH5{
        public static final String MWEB_URL = "mweb_url";
    }

    public static class evokeRefund {
        public static final String APPID = "appid";
        public static final String MCH_ID = "mch_id";
        public static final String NONCE_STR = "nonce_str";
        public static final String SIGN = "sign";
        public static final String TRANSACTION_ID = "transaction_id";
        public static final String OUT_TRADE_NO = "out_trade_no";
        public static final String OUT_REFUND_NO = "out_refund_no";
        public static final String TOTAL_FEE = "total_fee";
        public static final String REFUND_FEE = "refund_fee";

        public static final String RETURN_CODE = "return_code";
        public static final String RETURN_MSG = "return_msg";
        public static final String RESULT_CODE = "result_code";
        public static final String TRADE_STATE = "trade_state";
        public static final String REFUND_STATUS_$N = "refund_status_";
    }

    public static class evokeTransfers {
        public static final String MCH_APPID = "mch_appid";
        public static final String MCHID = "mchid";
        public static final String NONCE_STR = "nonce_str";
        public static final String SIGN = "sign";
        public static final String PARTNER_TRADE_NO = "partner_trade_no";
        public static final String OPENID = "openid";
        public static final String CHECK_NAME = "check_name";
        public static final String AMOUNT = "amount";
        public static final String DESC = "desc";
        public static final String SPBILL_CREATE_IP = "spbill_create_ip";
        public static final String RETURN_CODE = "return_code";
        public static final String RESULT_CODE = "result_code";
    }

    public static class evokeTransferInfo {
        public static final String APPID = "appid";
        public static final String MCH_ID = "mch_id";
        public static final String NONCE_STR = "nonce_str";
        public static final String SIGN = "sign";
        public static final String PARTNER_TRADE_NO = "partner_trade_no";

        public static final String RETURN_CODE = "return_code";
        public static final String STATUS = "status";
    }

    public static class oAuth2Scope {
        public static final String SNSAPI_BASE = "snsapi_base";
        public static final String SNSAPI_USERINFO = "snsapi_userinfo";
        public static final String SNSAPI_LOGIN = "snsapi_login";

    }

    public static class error {
        public static final String err_code = "errcode";
        public static final String err_msg = "errmsg";
        public static final String ERR_CODE = "err_code";
        public static final String ERR_CODE_DES = "err_code_des";
    }

    public static class Status {
        public static final String SUCCESS = "SUCCESS";
        public static final String FAIL = "FAIL";
    }

    /**
     * 微信支付统一接口(POST)
     */
    public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 微信退款接口(POST)
     */
    public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    /**
     * 订单查询接口(POST)
     */
    public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    /**
     * 查询退款接口(POST)
     */
    public final static String REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
    /**
     * 企业转账到钱包(POST)
     */
    public final static String TRANSFERS_WALLET_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    /**
     * 查询企业付款(POST)
     */
    public final static String GETTRANSFERINFO_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
    /**
     * 网站应用统一openId(GET)
     */
    public final static String UNIFIED_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /**
     * 网站应用统一拉取用户信息(GET)
     */
    public final static String UNIFIED_OAUTH2_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    /**
     * 公众号获取code(GET)
     */
    public final static String PP_CODE_AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

    /**
     * 公众扫一扫获取AccessToken(GET)
     */
    public final static String PP_SCAN_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    /**
     * 微信扫一扫获取JsApiTicket(GET)
     */
    public final static String PP_SCAN_JSAPITICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

    /**
     * 小程序获取openId(GET)
     */
    public final static String SP_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    /**
     * 小程序获取AccessToken(GET)
     */
    public final static String SP_GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    /**
     * 微信外WEB应用获取code
     */
    public final static String OP_CODE_AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

    /**
     * 微信小程序发送模板消息
     */
    public final static String SP_SEND_TEMPLATE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=%s";
    /**
     * 公众号获取用户基本信息(UnionID机制)
     */
    public final static String PP_GET_UNION_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
}
