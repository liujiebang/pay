package com.github.liujiebang.pay.wx.service;

import com.github.liujiebang.pay.wx.entity.WxSendMessageTemplate;

public interface WxSendMessageService {

    /**
     * 发送微信小程序消息模板
     *
     * @param wxSendMessageTemplate 模板对象
     * @param accessToken           接口调用凭证
     * @return 调用结果
     */
    String wxSpSendMessageTemplate(WxSendMessageTemplate wxSendMessageTemplate, String accessToken);
}
