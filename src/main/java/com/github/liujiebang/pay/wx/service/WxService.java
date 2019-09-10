package com.github.liujiebang.pay.wx.service;

import com.github.liujiebang.pay.wx.config.WxConfig;

public interface WxService {

    /**
     * 初始化微信各平台参数
     *
     * @param wxConfig 微信各平台参数对象
     */
    void setWxConfigStorage(WxConfig wxConfig);


}
