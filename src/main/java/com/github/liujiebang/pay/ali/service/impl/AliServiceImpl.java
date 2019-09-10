package com.github.liujiebang.pay.ali.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.github.liujiebang.pay.ali.service.AliService;
import com.github.liujiebang.pay.ali.config.AliPayConfig;
import com.github.liujiebang.pay.utils.PayException;

public class AliServiceImpl implements AliService {

    protected AliPayConfig aliPayConfig;
    protected AlipayClient alipayClient;

    @Override
    public void setAliPayConfigStorage(AliPayConfig aliPayConfig) {
        if (aliPayConfig == null) {
            throw new PayException("支付宝参数对象未初始化");
        }
        this.aliPayConfig = aliPayConfig;
        this.alipayClient = new DefaultAlipayClient(aliPayConfig.getGateway(), aliPayConfig.getAppId(),
                aliPayConfig.getPrivateKey(), aliPayConfig.getObject(), aliPayConfig.getCharset(),
                aliPayConfig.getAliPublicKey(), aliPayConfig.getSignType());
    }

}
