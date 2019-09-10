package com.github.liujiebang.pay.wx.service.impl;

import com.github.liujiebang.pay.wx.config.WxConfig;
import com.github.liujiebang.pay.wx.service.WxService;
public class WxServiceImpl implements WxService {

    protected WxConfig wxConfig;

    @Override
    public void setWxConfigStorage(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

}
