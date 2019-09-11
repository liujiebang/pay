package com.github.liujiebang.pay.ali.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.github.liujiebang.pay.ali.config.AliConfig;
import com.github.liujiebang.pay.ali.config.AliPayConfig;
import com.github.liujiebang.pay.ali.service.AliPayService;
import com.github.liujiebang.pay.utils.IdentityUtil;
import com.github.liujiebang.pay.utils.PayException;
import com.github.liujiebang.pay.wx.config.WxConfig;


public class AliPayServiceImpl implements AliPayService {

    @Override
    public String aliAppPay(String orderId, double payAmount, AliPayConfig aliPayConfig) {
        AlipayTradeAppPayResponse response = null;
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(aliPayConfig.getBody());
        model.setSubject(aliPayConfig.getSubject());
        model.setOutTradeNo(orderId);
        model.setTimeoutExpress(aliPayConfig.getTimeoutexpress());
        model.setTotalAmount(String.valueOf(payAmount));
        model.setProductCode(AliConfig.Product.PRODUCTCODE_APP);
        request.setBizModel(model);
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        try {
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                return response.getBody();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }

    @Override
    public String aliWebPay(String orderId, double payAmount, AliPayConfig aliPayConfig) {
        AlipayTradePagePayResponse response = null;
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(aliPayConfig.getBody());
        model.setOutTradeNo(orderId);
        model.setProductCode(AliConfig.Product.PRODUCTCODE_WEB);
        model.setTotalAmount(String.valueOf(payAmount));
        model.setSubject(aliPayConfig.getSubject());
        request.setBizModel(model);
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        try {
            response = getAlipayClient(aliPayConfig).pageExecute(request);
            if (response.isSuccess()) {
                return response.getBody();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }

    @Override
    public String aliWapPay(String orderId, double payAmount,AliPayConfig aliPayConfig) {
        AlipayTradeWapPayResponse response = null;
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(orderId);
        model.setSubject(aliPayConfig.getSubject());
        model.setBody(aliPayConfig.getBody());
        model.setTimeoutExpress(aliPayConfig.getTimeoutexpress());
        model.setTotalAmount(String.valueOf(payAmount));
        model.setProductCode(AliConfig.Product.PRODUCTCODE_WAP);
        model.setQuitUrl(aliPayConfig.getQuitUrl());
        request.setBizModel(model);
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        try {
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                return response.getBody();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }

    @Override
    public String aliSpPay(String orderId, double payAmount, String buyerId,AliPayConfig aliPayConfig) {
        AlipayTradeCreateResponse response = null;
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        AlipayTradeCreateModel model = new AlipayTradeCreateModel();
        model.setOutTradeNo(orderId);
        model.setTotalAmount(String.valueOf(payAmount));
        model.setSubject(aliPayConfig.getSubject());
        model.setBody(aliPayConfig.getBody());
        model.setBuyerId(buyerId);
        request.setBizModel(model);
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        try {
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return response.getTradeNo();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }

    @Override
    public String aliQrPay(String orderId, double payAmount,AliPayConfig aliPayConfig) {
        AlipayTradePrecreateResponse response = null;
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setOutTradeNo(orderId);
        model.setTotalAmount(String.valueOf(payAmount));
        model.setSubject(aliPayConfig.getSubject());
        model.setBody(aliPayConfig.getBody());
        request.setBizModel(model);
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        try {
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return response.getQrCode();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }

    @Override
    public String aliPay(String orderId, double payAmount, String buyerId, String payType,AliPayConfig aliPayConfig) {
        String aliData = null;
        if (AliConfig.payType.ALI_APP.equals(payType)) {
            aliData = aliAppPay(orderId, payAmount,aliPayConfig);
        } else if (AliConfig.payType.ALI_WEB.equals(payType)) {
            aliData = aliWebPay(orderId, payAmount,aliPayConfig);
        } else if (AliConfig.payType.ALI_WAP.equals(payType)) {
            aliData = aliWapPay(orderId, payAmount,aliPayConfig);
        } else if (AliConfig.payType.ALI_SP.equals(payType)) {
            aliData = aliSpPay(orderId, payAmount, buyerId,aliPayConfig);
        } else if (AliConfig.payType.ALI_QR.equals(payType)) {
            aliData = aliQrPay(orderId, payAmount,aliPayConfig);
        }
        return aliData;
    }

    @Override
    public boolean aliPayReturn(String orderId, double payAmount,AliPayConfig aliPayConfig) throws PayException {
        AlipayTradeRefundResponse response = null;
        try {
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            AlipayTradeRefundModel model = new AlipayTradeRefundModel();
            model.setOutTradeNo(orderId);
            model.setRefundAmount(String.valueOf(payAmount));
            model.setOutRequestNo(IdentityUtil.uuid());
            request.setBizModel(model);
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return true;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }

    @Override
    public boolean aliPayReturnQuery(String orderId,AliPayConfig aliPayConfig) throws PayException {
        AlipayTradeFastpayRefundQueryResponse response = null;
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutRequestNo(orderId);
        model.setOutTradeNo(orderId);
        request.setBizModel(model);
        try {
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return true;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }

    @Override
    public boolean aliPayTransfer(String aliPayAccount, double payAmount,AliPayConfig aliPayConfig) throws PayException {
        AlipayFundTransToaccountTransferResponse response = null;
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
        model.setAmount(String.valueOf(payAmount));
        model.setOutBizNo(IdentityUtil.uuid());
        model.setPayeeAccount(aliPayAccount);
        model.setPayeeType(AliConfig.PayeeType.ALIPAY_LOGONID);
        request.setBizModel(model);
        try {
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.execute(request);
            if (response.isSuccess() && !StringUtils.isEmpty(response.getPayDate())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }


    @Override
    public String aliPayQuery(String orderId,AliPayConfig aliPayConfig) throws PayException {
        AlipayTradeQueryResponse response = null;
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(orderId);
        request.setBizModel(model);
        try {
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return response.getTradeStatus();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }

    @Override
    public boolean aliPayTransQeury(String orderId,AliPayConfig aliPayConfig) throws PayException {
        AlipayFundTransOrderQueryResponse response = null;
        try {
            AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
            AlipayFundTransOrderQueryModel model = new AlipayFundTransOrderQueryModel();
            model.setOutBizNo(orderId);
            request.setBizModel(model);
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.execute(request);
            if (response.isSuccess() && ("SUCCESS".equalsIgnoreCase(response.getStatus()))) {
                return true;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }


    @Override
    public AlipayFundAuthOrderAppFreezeResponse aliPayAuthFreeze(String outOrderNo, String requestNo, double payAmount, String orderTitle, String extraParam, AliPayConfig aliPayConfig) throws PayException {
        AlipayFundAuthOrderAppFreezeResponse response = null;

        try {
            AlipayFundAuthOrderAppFreezeRequest request = new AlipayFundAuthOrderAppFreezeRequest();
            AlipayFundAuthOrderAppFreezeModel model = new AlipayFundAuthOrderAppFreezeModel();
            if (orderTitle == null || "".equals(orderTitle))
                model.setOrderTitle("支付宝资金授权");
            else
                model.setOrderTitle(orderTitle);
            model.setOutOrderNo(outOrderNo);
            model.setOutRequestNo(requestNo);
            model.setAmount(String.valueOf(payAmount));
            model.setProductCode(AliConfig.Product.PRODUCTCODE_AUTH_ONLINE);
            model.setPayeeUserId(aliPayConfig.getPayeeUserId());
            model.setEnablePayChannels("[{\"payChannelType\":\"PCREDIT_PAY\"},{\"payChannelType\":\"MONEY_FUND\"},{\"payChannelType\":\"CREDITZHIMA\"}]");
            model.setExtraParam(extraParam);
            request.setBizModel(model);
            request.setNotifyUrl(aliPayConfig.getFundAuthNotifyUrl());
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                return response;
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }

    @Override
    public boolean aliPayTradePay(String aliUserId, String outOrderNo, String authNo, String storeId, double payAmount,AliPayConfig aliPayConfig) throws PayException {
        AlipayTradePayResponse response = null;
        try {
            AlipayTradePayRequest request = new AlipayTradePayRequest();
            AlipayTradePayModel model = new AlipayTradePayModel();
            model.setSubject(aliPayConfig.getSubject());
            model.setBody(aliPayConfig.getBody());
            model.setOutTradeNo(outOrderNo);
            model.setBuyerId(aliUserId);
            model.setAuthNo(authNo);
            model.setSellerId(aliPayConfig.getPayeeUserId());
            model.setStoreId(storeId == null ? "" : storeId);
            model.setProductCode(AliConfig.Product.PRODUCTCODE_AUTH_ONLINE);
            model.setTotalAmount(String.valueOf(payAmount));
            model.setAuthConfirmMode(AliConfig.PRE_AUTH.COMPLETE);
            request.setBizModel(model);
            request.setNotifyUrl(aliPayConfig.getNotifyUrl());
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return true;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }


    @Override
    public boolean aliPayAuthUnFreeze(String outOrderNo, String authNo, double payAmount, String remark,AliPayConfig aliPayConfig) throws PayException {
        AlipayFundAuthOrderUnfreezeResponse response = null;
        try {
            AlipayFundAuthOrderUnfreezeRequest request = new AlipayFundAuthOrderUnfreezeRequest();
            AlipayFundAuthOrderUnfreezeModel model = new AlipayFundAuthOrderUnfreezeModel();
            if (remark == null || "".equals(remark))
                model.setRemark("支付宝资金解冻");
            else
                model.setRemark(remark);
            model.setOutRequestNo(outOrderNo);
            model.setAuthNo(authNo);
            model.setAmount(String.valueOf(payAmount));
            request.setBizModel(model);
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.execute(request);
            if (response.isSuccess() && ("SUCCESS".equalsIgnoreCase(response.getStatus()))) {
                return true;
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }

    @Override
    public AlipayFundAuthOperationDetailQueryResponse aliPayAuthOperationQuery(String outOrderNo, String requestNo,AliPayConfig aliPayConfig) throws PayException {
        AlipayFundAuthOperationDetailQueryResponse response = null;
        try {
            AlipayFundAuthOperationDetailQueryRequest request = new AlipayFundAuthOperationDetailQueryRequest();
            AlipayFundAuthOperationDetailQueryModel model = new AlipayFundAuthOperationDetailQueryModel();
            model.setOutRequestNo(outOrderNo);
            model.setOutRequestNo(requestNo);
            request.setBizModel(model);
            AlipayClient alipayClient=getAlipayClient(aliPayConfig);
            response = alipayClient.execute(request);
            if (response.isSuccess() && ("SUCCESS".equalsIgnoreCase(response.getStatus()))) {
                return response;
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new PayException(response.getSubMsg());
    }

    /**
     * 通用的获取AlipayClient对象
     * @param aliPayConfig
     * @return
     */
    private AlipayClient getAlipayClient(AliPayConfig aliPayConfig){
        AlipayClient alipayClient=new DefaultAlipayClient(aliPayConfig.getGateway(), aliPayConfig.getAppId(),
                aliPayConfig.getPrivateKey(), aliPayConfig.getObject(), aliPayConfig.getCharset(),
                aliPayConfig.getAliPublicKey(), aliPayConfig.getSignType());
        return alipayClient;

    }



}
