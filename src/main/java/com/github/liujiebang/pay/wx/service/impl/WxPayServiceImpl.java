package com.github.liujiebang.pay.wx.service.impl;

import com.github.liujiebang.pay.utils.*;
import com.github.liujiebang.pay.wx.config.WxConfig;
import com.github.liujiebang.pay.wx.entity.WxRequest;
import com.github.liujiebang.pay.wx.service.WxPayService;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class WxPayServiceImpl implements WxPayService {


    @Override
    public Map wxAppPay(String orderId, double payAmount, WxConfig wxConfig) {
        String prepayId = wxPayInitialization(orderId, payAmount, null, WxRequest.WX_APP_PAY,wxConfig);
        Map<String, String> map = new TreeMap<String, String>();
        map.put(WxRequest.evokePaymentAPP.APPID, wxConfig.getOpAppId());
        map.put(WxRequest.evokePaymentAPP.PARTNERID, wxConfig.getOpMchId());
        map.put(WxRequest.evokePaymentAPP.PREPAYID, prepayId);
        map.put(WxRequest.evokePaymentAPP.PACKAGE, "Sign=WXPay");
        map.put(WxRequest.evokePaymentAPP.NONCESTR, IdentityUtil.uuid());
        map.put(WxRequest.evokePaymentAPP.TIMESTAMP, IdentityUtil.getTimeStamp());
        try {
            map.put(WxRequest.evokePaymentAPP.SIGN, IdentityUtil.createSign(map, wxConfig.getOpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map wxPpPay(String orderId, double payAmount, String openId,WxConfig wxConfig) {
        String prepayId = wxPayInitialization(orderId, payAmount, openId, WxRequest.WX_PP_PAY,wxConfig);
        Map<String, String> map = new TreeMap<String, String>();
        map.put(WxRequest.evokePaymentJSAPI.APPID, wxConfig.getPpAppId());
        map.put(WxRequest.evokePaymentJSAPI.TIMESTAMP, IdentityUtil.getTimeStamp());
        map.put(WxRequest.evokePaymentJSAPI.NONCESTR, IdentityUtil.uuid());
        map.put(WxRequest.evokePaymentJSAPI.PACKAGE, "prepay_id=" + prepayId);
        map.put(WxRequest.evokePaymentJSAPI.SIGNTYPE, IdentityUtil.SIGN_TYPE_MD5);
        try {
            map.put(WxRequest.evokePaymentJSAPI.PAYSIGN, IdentityUtil.createSign(map, wxConfig.getPpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return map;
    }


    @Override
    public Map wxWebPay(String orderId, double payAmount,WxConfig wxConfig) {
        String codeUrl = wxPayInitialization(orderId, payAmount, null, WxRequest.WX_WEB_PAY,wxConfig);
        Map<String, String> map = new TreeMap<String, String>();
        map.put(WxRequest.evokePaymentNATIVE.CODE_URL, codeUrl);
        return map;
    }

    @Override
    public Map wxSpPay(String orderId, double payAmount, String openId,WxConfig wxConfig) {
        String prepayId = wxPayInitialization(orderId, payAmount, openId, WxRequest.WX_SP_PAY,wxConfig);
        Map<String, String> map = new TreeMap<String, String>();
        map.put(WxRequest.evokePaymentJSAPI.APPID, wxConfig.getSpAppId());
        map.put(WxRequest.evokePaymentJSAPI.TIMESTAMP, IdentityUtil.getTimeStamp());
        map.put(WxRequest.evokePaymentJSAPI.NONCESTR, IdentityUtil.uuid());
        map.put(WxRequest.evokePaymentJSAPI.PACKAGE, "prepay_id=" + prepayId);
        map.put(WxRequest.evokePaymentJSAPI.SIGNTYPE, IdentityUtil.SIGN_TYPE_MD5);
        try {
            map.put(WxRequest.evokePaymentJSAPI.PAYSIGN, IdentityUtil.createSign(map, wxConfig.getSpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map wxPay(String orderId, double payAmount, String openId, String payType,WxConfig wxConfig) {
        Map<String, String> map = null;
        if (WxRequest.WX_APP_PAY.equals(payType)) {
            map = wxAppPay(orderId, payAmount,wxConfig);
        } else if (WxRequest.WX_WEB_PAY.equals(payType)) {
            map = wxWebPay(orderId, payAmount,wxConfig);
        } else if (WxRequest.WX_PP_PAY.equals(payType)) {
            map = wxPpPay(orderId, payAmount, openId,wxConfig);
        } else if (WxRequest.WX_SP_PAY.equals(payType)) {
            map = wxSpPay(orderId, payAmount, openId,wxConfig);
        }
        return map;
    }


    @Override
    public boolean wxReturn(String transactionId, String outTradeNo, String outRefundNo, double allMoney, double money, String payType,WxConfig wxConfig) {
        String certPath = null;
        Map<String, String> map = new TreeMap<String, String>();
        map.put(WxRequest.evokeRefund.NONCE_STR, IdentityUtil.uuid());
        map.put(WxRequest.evokeRefund.TRANSACTION_ID, transactionId);
        map.put(WxRequest.evokeRefund.OUT_TRADE_NO, outTradeNo);
        map.put(WxRequest.evokeRefund.OUT_REFUND_NO, outRefundNo);
        map.put(WxRequest.evokeRefund.TOTAL_FEE, IdentityUtil.getMoeny(allMoney));
        map.put(WxRequest.evokeRefund.REFUND_FEE, IdentityUtil.getMoeny(money));
        try {
            if (WxRequest.WX_PP_PAY.equals(payType)) {
                map.put(WxRequest.evokeRefund.APPID, wxConfig.getPpAppId());
                map.put(WxRequest.evokeRefund.MCH_ID, wxConfig.getPpMchId());
                map.put(WxRequest.evokeRefund.SIGN, IdentityUtil.createSign(map, wxConfig.getPpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
                certPath = wxConfig.getPpCertPath();
            } else if (WxRequest.WX_APP_PAY.equals(payType) || WxRequest.WX_WEB_PAY.equals(payType)) {
                map.put(WxRequest.evokeRefund.APPID, wxConfig.getOpAppId());
                map.put(WxRequest.evokeRefund.MCH_ID, wxConfig.getOpMchId());
                map.put(WxRequest.evokeRefund.SIGN, IdentityUtil.createSign(map, wxConfig.getOpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
                certPath = wxConfig.getOpCertPath();
            } else if (WxRequest.WX_SP_PAY.equals(payType)) {
                map.put(WxRequest.evokeRefund.APPID, wxConfig.getSpAppId());
                map.put(WxRequest.evokeRefund.MCH_ID, wxConfig.getSpMchId());
                map.put(WxRequest.evokeRefund.SIGN, IdentityUtil.createSign(map, wxConfig.getSpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
                certPath = wxConfig.getSpCertPath();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            String result = ClientCustomSSL.doRefund(WxRequest.REFUND_URL, XMLUtil.mapToXml(map), certPath, map.get(WxRequest.evokeRefund.MCH_ID));
            map = XMLUtil.xmlToMap(result);
        } catch (Exception e) {
            throw new PayException("签名错误:" + e.getMessage());
        }
        String returnCode = map.get(WxRequest.evokeRefund.RETURN_CODE);
        String resultCode = map.get(WxRequest.evokeRefund.RESULT_CODE);
        if (WxRequest.Status.SUCCESS.equals(returnCode) && WxRequest.Status.SUCCESS.equals(resultCode)) {
            return true;
        } else if (WxRequest.Status.FAIL.equals(returnCode)) {
            throw new PayException(map.get(WxRequest.evokeRefund.RETURN_MSG));
        }
        throw new PayException(map.get(WxRequest.error.ERR_CODE_DES));
    }

    @Override
    public String wxQuery(String orderId, String payType,WxConfig wxConfig) throws PayException {
        Map<String, String> map = wxQueryInitialization(orderId, payType,wxConfig);
        try {
            String result = HttpClientUtil.httpsRequest(WxRequest.CHECK_ORDER_URL, HttpClientUtil.POST, XMLUtil.mapToXml(map));
            map = XMLUtil.xmlToMap(result);
            String returnCode = map.get(WxRequest.evokeRefund.RETURN_CODE);
            String resultCode = map.get(WxRequest.evokeRefund.RESULT_CODE);
            if (WxRequest.Status.SUCCESS.equals(returnCode) && WxRequest.Status.SUCCESS.equals(resultCode)) {
                return map.get(WxRequest.evokeRefund.TRADE_STATE);
            } else if (WxRequest.Status.FAIL.equals(returnCode)) {
                throw new PayException(map.get(WxRequest.evokeRefund.RETURN_MSG));
            }
        } catch (Exception e) {
            throw new PayException("签名错误:" + e.getMessage());
        }
        throw new PayException(map.get(WxRequest.error.ERR_CODE_DES));
    }

    @Override
    public String wxRefundQuery(String orderId, String payType,WxConfig wxConfig) throws PayException {
        Map<String, String> map = wxQueryInitialization(orderId, payType,wxConfig);
        try {
            String result = HttpClientUtil.httpsRequest(WxRequest.REFUND_QUERY_URL, HttpClientUtil.POST, XMLUtil.mapToXml(map));
            map = XMLUtil.xmlToMap(result);
            String code = map.get(WxRequest.evokeRefund.RETURN_CODE);
            if (WxRequest.Status.SUCCESS.equals(code)) {
                List<String> stringList = XMLUtil.getLikeByMap(map, WxRequest.evokeRefund.REFUND_STATUS_$N);
                if (stringList != null && stringList.size() > 0) {
                    return stringList.get(stringList.size() - 1);
                }
            } else if (WxRequest.Status.FAIL.equals(code)) {
                throw new PayException(map.get(WxRequest.evokeRefund.RETURN_MSG));
            }

        } catch (Exception e) {
            throw new PayException("签名错误:" + e.getMessage());
        }
        throw new PayException(map.get(WxRequest.error.ERR_CODE_DES));
    }

    @Override
    public boolean wxTransfers(String orderId, double payAmount, String openId, String desc, String payType,WxConfig wxConfig) throws PayException {
        String certPath = null;
        Map<String, String> map = new TreeMap<String, String>();
        map.put(WxRequest.evokeTransfers.NONCE_STR, IdentityUtil.uuid());
        map.put(WxRequest.evokeTransfers.PARTNER_TRADE_NO, orderId);
        map.put(WxRequest.evokeTransfers.OPENID, openId);
        map.put(WxRequest.evokeTransfers.CHECK_NAME, "NO_CHECK");
        map.put(WxRequest.evokeTransfers.AMOUNT, IdentityUtil.getMoeny(payAmount));
        map.put(WxRequest.evokeTransfers.DESC, desc);
        map.put(WxRequest.evokeTransfers.SPBILL_CREATE_IP, IdentityUtil.getLocalhostIp());
        try {
            if (WxRequest.WX_PP_PAY.equals(payType)) {
                map.put(WxRequest.evokeTransfers.MCH_APPID, wxConfig.getPpAppId());
                map.put(WxRequest.evokeTransfers.MCHID, wxConfig.getPpMchId());
                map.put(WxRequest.evokeTransfers.SIGN, IdentityUtil.createSign(map, wxConfig.getPpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
                certPath = wxConfig.getPpCertPath();
            } else if (WxRequest.WX_APP_PAY.equals(payType) || WxRequest.WX_WEB_PAY.equals(payType)) {
                map.put(WxRequest.evokeTransfers.MCH_APPID, wxConfig.getOpAppId());
                map.put(WxRequest.evokeTransfers.MCHID, wxConfig.getOpMchId());
                map.put(WxRequest.evokeTransfers.SIGN, IdentityUtil.createSign(map, wxConfig.getOpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
                certPath = wxConfig.getOpCertPath();
            } else if (WxRequest.WX_SP_PAY.equals(payType)) {
                map.put(WxRequest.evokeTransfers.MCH_APPID, wxConfig.getSpAppId());
                map.put(WxRequest.evokeTransfers.MCHID, wxConfig.getSpMchId());
                map.put(WxRequest.evokeTransfers.SIGN, IdentityUtil.createSign(map, wxConfig.getSpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
                certPath = wxConfig.getSpCertPath();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            String result = ClientCustomSSL.doRefund(WxRequest.TRANSFERS_WALLET_URL, XMLUtil.mapToXml(map), certPath, map.get(WxRequest.evokeTransfers.MCHID));
            map = XMLUtil.xmlToMap(result);
            String returnCode = map.get(WxRequest.evokeTransfers.RETURN_CODE);
            String resultCode = map.get(WxRequest.evokeTransfers.RESULT_CODE);
            if (WxRequest.Status.SUCCESS.equals(returnCode) && WxRequest.Status.SUCCESS.equals(resultCode)) {
                return true;
            } else if (WxRequest.Status.FAIL.equals(returnCode)) {
                throw new PayException(map.get(WxRequest.evokeRefund.RETURN_MSG));
            }
        } catch (Exception e) {
            throw new PayException("签名错误:" + e.getMessage());
        }
        throw new PayException(map.get(WxRequest.error.ERR_CODE_DES));
    }

    @Override
    public String wxGetTransferInfo(String orderId, String payType,WxConfig wxConfig) throws PayException {
        String certPath = null;
        Map<String, String> map = new TreeMap<String, String>();
        map.put(WxRequest.evokeTransferInfo.NONCE_STR, IdentityUtil.uuid());
        map.put(WxRequest.evokeTransferInfo.PARTNER_TRADE_NO, orderId);
        try {
            if (WxRequest.WX_PP_PAY.equals(payType)) {
                map.put(WxRequest.evokeTransferInfo.APPID, wxConfig.getPpAppId());
                map.put(WxRequest.evokeTransferInfo.MCH_ID, wxConfig.getPpMchId());
                map.put(WxRequest.evokeTransferInfo.SIGN, IdentityUtil.createSign(map, wxConfig.getPpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
                certPath = wxConfig.getPpCertPath();
            } else if (WxRequest.WX_APP_PAY.equals(payType) || WxRequest.WX_WEB_PAY.equals(payType)) {
                map.put(WxRequest.evokeTransferInfo.APPID, wxConfig.getOpAppId());
                map.put(WxRequest.evokeTransferInfo.MCH_ID, wxConfig.getOpMchId());
                map.put(WxRequest.evokeTransferInfo.SIGN, IdentityUtil.createSign(map, wxConfig.getOpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
                certPath = wxConfig.getOpCertPath();
            } else if (WxRequest.WX_SP_PAY.equals(payType)) {
                map.put(WxRequest.evokeTransferInfo.APPID, wxConfig.getSpAppId());
                map.put(WxRequest.evokeTransferInfo.MCH_ID, wxConfig.getSpMchId());
                map.put(WxRequest.evokeTransferInfo.SIGN, IdentityUtil.createSign(map, wxConfig.getSpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
                certPath = wxConfig.getSpCertPath();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            String result = ClientCustomSSL.doRefund(WxRequest.GETTRANSFERINFO_URL, XMLUtil.mapToXml(map), certPath, map.get(WxRequest.evokeTransferInfo.MCH_ID));
            map = XMLUtil.xmlToMap(result);
            String code = map.get(WxRequest.evokeTransferInfo.RETURN_CODE);
            if (WxRequest.Status.SUCCESS.equals(code)) {
                return map.get(WxRequest.evokeTransferInfo.STATUS);
            } else if (WxRequest.Status.FAIL.equals(code)) {
                throw new PayException(map.get(WxRequest.evokeRefund.RETURN_MSG));
            }
        } catch (Exception e) {
            throw new PayException("签名错误:" + e.getMessage());
        }
        throw new PayException(map.get(WxRequest.error.ERR_CODE_DES));
    }

    private Map<String, String> wxQueryInitialization(String orderId, String payType,WxConfig wxConfig) {
        Map<String, String> map = new TreeMap<String, String>();
        map.put(WxRequest.evokeRefund.NONCE_STR, IdentityUtil.uuid());
        map.put(WxRequest.evokeRefund.OUT_TRADE_NO, orderId);
        try {
            if (WxRequest.WX_PP_PAY.equals(payType)) {
                map.put(WxRequest.evokeRefund.APPID, wxConfig.getPpAppId());
                map.put(WxRequest.evokeRefund.MCH_ID, wxConfig.getPpMchId());
                map.put(WxRequest.evokeRefund.SIGN, IdentityUtil.createSign(map, wxConfig.getPpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
            } else if (WxRequest.WX_APP_PAY.equals(payType) || WxRequest.WX_WEB_PAY.equals(payType)) {
                map.put(WxRequest.evokeRefund.APPID, wxConfig.getOpAppId());
                map.put(WxRequest.evokeRefund.MCH_ID, wxConfig.getOpMchId());
                map.put(WxRequest.evokeRefund.SIGN, IdentityUtil.createSign(map, wxConfig.getOpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
            } else if (WxRequest.WX_SP_PAY.equals(payType)) {
                map.put(WxRequest.evokeRefund.APPID, wxConfig.getSpAppId());
                map.put(WxRequest.evokeRefund.MCH_ID, wxConfig.getSpMchId());
                map.put(WxRequest.evokeRefund.SIGN, IdentityUtil.createSign(map, wxConfig.getSpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return map;
    }

    private String wxPayInitialization(String orderId, double payAmount, String openId, String payType,WxConfig wxConfig) throws PayException {
        Map<String, String> map = new TreeMap<String, String>();
        try {
            map.put(WxRequest.unifiedOrder.BODY, wxConfig.getBody());
            map.put(WxRequest.unifiedOrder.NONCE_STR, IdentityUtil.uuid());
            map.put(WxRequest.unifiedOrder.NOTIFY_URL, wxConfig.getNotifyUrl());
            map.put(WxRequest.unifiedOrder.OUT_TRADE_NO, orderId);
            map.put(WxRequest.unifiedOrder.SPBILL_CREATE_IP, IdentityUtil.getLocalhostIp());
            map.put(WxRequest.unifiedOrder.TOTAL_FEE, IdentityUtil.getMoeny(payAmount));
            if (WxRequest.WX_PP_PAY.equals(payType)) {
                map.put(WxRequest.unifiedOrder.APPID, wxConfig.getPpAppId());
                map.put(WxRequest.unifiedOrder.MCH_ID, wxConfig.getPpMchId());
                map.put(WxRequest.unifiedOrder.OPENID, openId);
                map.put(WxRequest.unifiedOrder.TRADE_TYPE, WxRequest.tradeType.JSAPI);
                map.put(WxRequest.unifiedOrder.SIGN, IdentityUtil.createSign(map, wxConfig.getPpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
            } else if (WxRequest.WX_APP_PAY.equals(payType)) {
                map.put(WxRequest.unifiedOrder.APPID, wxConfig.getOpAppId());
                map.put(WxRequest.unifiedOrder.MCH_ID, wxConfig.getOpMchId());
                map.put(WxRequest.unifiedOrder.TRADE_TYPE, WxRequest.tradeType.APP);
                map.put(WxRequest.unifiedOrder.SIGN, IdentityUtil.createSign(map, wxConfig.getOpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
            } else if (WxRequest.WX_WEB_PAY.equals(payType)) {
                map.put(WxRequest.unifiedOrder.APPID, wxConfig.getOpAppId());
                map.put(WxRequest.unifiedOrder.MCH_ID, wxConfig.getOpMchId());
                map.put(WxRequest.unifiedOrder.TRADE_TYPE, WxRequest.tradeType.NATIVE);
                map.put(WxRequest.unifiedOrder.SIGN, IdentityUtil.createSign(map, wxConfig.getOpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
            } else if (WxRequest.WX_SP_PAY.equals(payType)) {
                map.put(WxRequest.unifiedOrder.APPID, wxConfig.getSpAppId());
                map.put(WxRequest.unifiedOrder.MCH_ID, wxConfig.getSpMchId());
                map.put(WxRequest.unifiedOrder.OPENID, openId);
                map.put(WxRequest.unifiedOrder.TRADE_TYPE, WxRequest.tradeType.JSAPI);
                map.put(WxRequest.unifiedOrder.SIGN, IdentityUtil.createSign(map, wxConfig.getSpMchKey(), IdentityUtil.SIGN_TYPE_MD5));
            }
        } catch (Exception e) {
            throw new PayException("未初始化参数");
        }
        try {
            String xml = XMLUtil.mapToXml(map);
            String result = HttpClientUtil.httpsRequest(WxRequest.UNIFIED_ORDER_URL, HttpClientUtil.POST, xml);
            if (WxRequest.WX_WEB_PAY.equals(payType)) {
                return XMLUtil.xmlToMap(result).get(WxRequest.evokePaymentNATIVE.CODE_URL);
            }
            return XMLUtil.xmlToMap(result).get(WxRequest.unifiedOrder.PREPAY_ID);
        } catch (Exception e) {
            throw new PayException("参数解析失败:" + e.getMessage());
        }
    }

}
