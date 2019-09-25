package com.github.liujiebang.pay.wx.service;

import com.github.liujiebang.pay.wx.entity.WxPpSendMessageTemplate;
import com.github.liujiebang.pay.wx.entity.WxSpSendMessageTemplate;

public interface WxSendMessageService {

    /**
     * 发送微信小程序消息模板
     *
     * @param wxSpSendMessageTemplate 模板对象
     * @param accessToken             接口调用凭证
     * @return 调用结果
     */
    String wxSpSendMessageTemplate(WxSpSendMessageTemplate wxSpSendMessageTemplate, String accessToken);

    /**
     * 发送微信公众号消息模板
     *
     * @param wxPpSendMessageTemplate 模板对象
     * @param accessToken             接口调用凭证
     * @return 调用结果
     */
    String wxPpSendMessageTemplate(WxPpSendMessageTemplate wxPpSendMessageTemplate, String accessToken);
}
