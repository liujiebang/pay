package com.github.liujiebang.pay.ali.service;


import com.alipay.api.response.AlipayFundAuthOperationDetailQueryResponse;
import com.alipay.api.response.AlipayFundAuthOrderAppFreezeResponse;
import com.github.liujiebang.pay.ali.config.AliPayConfig;
import com.github.liujiebang.pay.utils.PayException;

public interface AliPayService {

    /**
     * 支付宝APP支付
     *
     * @param orderId   支付单号
     * @param payAmount 支付金额
     * @return 返回支付签名
     */
    String aliAppPay(String orderId, double payAmount,AliPayConfig aliPayConfig);

    /**
     * 支付宝网页支付
     *
     * @param orderId   支付单号
     * @param payAmount 支付金额
     * @return 返回form表单
     */
    String aliWebPay(String orderId, double payAmount,AliPayConfig aliPayConfig);

    /**
     * 支付宝手机网站支付
     *
     * @param orderId   支付单号
     * @param payAmount 支付金额
     * @return 返回支付签名
     */
    String aliWapPay(String orderId, double payAmount,AliPayConfig aliPayConfig);

    /**
     * 支付宝小程序支付
     *
     * @param orderId   支付单号
     * @param payAmount 支付金额
     * @param buyerId   支付宝用户标识
     * @return 返回支付宝交易号
     */
    String aliSpPay(String orderId, double payAmount, String buyerId,AliPayConfig aliPayConfig);

    /**
     * 支付宝线下扫码支付
     *
     * @param orderId   支付订单
     * @param payAmount 支付金额
     * @return 用于生成二维码码串
     */
    String aliQrPay(String orderId, double payAmount,AliPayConfig aliPayConfig);

    /**
     * 支付宝统一支付
     *
     * @param orderId   支付单号
     * @param payAmount 支付金额
     * @param payType   支付渠道
     * @return 返回支付签名或form表单
     */
    String aliPay(String orderId, double payAmount, String buyerId, String payType,AliPayConfig aliPayConfig);

    /**
     * 支付宝退款
     *
     * @param orderId   退款单号
     * @param payAmount 退款金额
     * @return true:退款发起成功
     * @throws PayException
     */
    boolean aliPayReturn(String orderId, double payAmount,AliPayConfig aliPayConfig) throws PayException;

    /**
     * 支付宝退款订单查询
     *
     * @param orderId 退款单号
     * @return true:退款成功
     * @throws PayException
     */
    boolean aliPayReturnQuery(String orderId,AliPayConfig aliPayConfig) throws PayException;

    /**
     * 支付宝转账
     *
     * @param aliPayAccount 支付宝转账账号
     * @param payAmount     转账金额
     * @return true:转账发起成功
     * @throws PayException
     */
    boolean aliPayTransfer(String aliPayAccount, double payAmount,AliPayConfig aliPayConfig) throws PayException;

    /**
     * 支付订单查询
     *
     * @param orderId 支付单号
     * @return 返回订单当前状态
     * @throws PayException
     */
    String aliPayQuery(String orderId,AliPayConfig aliPayConfig) throws PayException;

    /**
     * 转账订单查询
     *
     * @param orderId 支付单号
     * @return true:转账成功
     * @throws PayException
     */
    boolean aliPayTransQeury(String orderId,AliPayConfig aliPayConfig) throws PayException;

    /**
     * 线上资金授权冻结
     *
     * @param outOrderNo 授权订单号
     * @param requestNo  请求流水号
     * @param payAmount  授权金额
     * @param orderTitle 业务订单描述
     * @param extraParam 业务拓展参数
     * @return
     * @throws PayException
     */
    AlipayFundAuthOrderAppFreezeResponse aliPayAuthFreeze(String outOrderNo, String requestNo, double payAmount, String orderTitle, String extraParam,AliPayConfig aliPayConfig) throws PayException;

    /**
     * 资金授权统一支付
     *
     * @param aliUserId  支付宝唯一标识
     * @param outOrderNo 授权订单号
     * @param authNo     支付授权码
     * @param storeId    实际支付的终端编号与outStoreCode保持一致
     * @param payAmount  支付授权金额
     * @return
     * @throws PayException
     */
    boolean aliPayTradePay(String aliUserId, String outOrderNo, String authNo, String storeId, double payAmount,AliPayConfig aliPayConfig) throws PayException;

    /**
     * 线上资金授权解冻
     *
     * @param outOrderNo 授权订单号
     * @param authNo     支付授权码
     * @param payAmount  授权解冻金额
     * @param remark     描述
     * @return
     * @throws PayException
     */
    boolean aliPayAuthUnFreeze(String outOrderNo, String authNo, double payAmount, String remark,AliPayConfig aliPayConfig) throws PayException;

    /**
     * 资金授权操作查询
     *
     * @param outOrderNo 授权订单号
     * @param requestNo  请求流水号
     * @return
     * @throws PayException
     */
    AlipayFundAuthOperationDetailQueryResponse aliPayAuthOperationQuery(String outOrderNo, String requestNo,AliPayConfig aliPayConfig) throws PayException;
}
