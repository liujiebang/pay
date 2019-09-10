package com.github.liujiebang.pay.wx.service;


import com.github.liujiebang.pay.utils.PayException;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface WxPayService extends WxService {

    /**
     * 微信APP支付
     *
     * @param orderId   支付单号
     * @param payAmount 支付金额
     * @return 微信支付签名
     */
    Map wxAppPay(String orderId, double payAmount);

    /**
     * 微信公众号支付
     *
     * @param orderId   支付单号
     * @param payAmount 支付金额
     * @param openId    授权用户唯一标识
     * @return 微信支付签名
     */
    Map wxPpPay(String orderId, double payAmount, String openId);

    /**
     * 微信网页支付
     *
     * @param orderId   支付单号
     * @param payAmount 支付金额
     * @return 网页支付链接（需将该链接生成二维码支付）
     */
    Map wxWebPay(String orderId, double payAmount);

    /**
     * 小程序支付
     *
     * @param orderId   支付单号
     * @param payAmount 支付金额
     * @param openId    授权用户唯一标识
     * @return 微信支付签名
     */
    Map wxSpPay(String orderId, double payAmount, String openId);

    /**
     * 微信退款
     *
     * @param transactionId  微信订单号
     * @param outTradeNo  商户订单号
     * @param outRefundNo 商户退款单号
     * @param allMoney    总金额
     * @param money       退款金额
     * @param payType     退款渠道
     * @return true:微信退款发起成功
     */
    boolean wxReturn(String transactionId, String outTradeNo, String outRefundNo, double allMoney, double money, String payType) throws PayException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException;

    /**
     * 微信订单查询
     *
     * @param orderId 支付单号
     * @param payType 支付渠道
     * @return 订单状态
     */
    String wxQuery(String orderId, String payType) throws PayException;

    /**
     * 微信退款查询
     *
     * @param orderId 支付单号
     * @param payType 支付渠道
     * @return 订单状态
     */
    String wxRefundQuery(String orderId, String payType) throws PayException;

    /**
     * 微信企业付款
     *
     * @param orderId   支付单号
     * @param payAmount 支付金额
     * @param openId    商户appid下，某用户的openid
     * @param desc      企业付款金额，必填
     * @param payType   支付渠道
     * @return true:企业付款发起成功
     */
    boolean wxTransfers(String orderId, double payAmount, String openId, String desc, String payType) throws PayException;

    /**
     * 查询企业付款
     *
     * @param orderId 支付单号
     * @param payType 支付渠道
     * @return 订单状态
     */
    String wxGetTransferInfo(String orderId, String payType) throws PayException;

    /**
     * 微信统一支付API
     *
     * @param orderId   订单号
     * @param payAmount 支付金额
     * @param openId    授权用户唯一标识
     * @param payType   支付渠道
     * @return
     */
    Map wxPay(String orderId, double payAmount, String openId, String payType);

}
