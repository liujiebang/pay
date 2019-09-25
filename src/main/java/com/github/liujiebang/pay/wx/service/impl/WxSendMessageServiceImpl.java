package com.github.liujiebang.pay.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.liujiebang.pay.utils.HttpClientUtil;
import com.github.liujiebang.pay.wx.entity.*;
import com.github.liujiebang.pay.wx.service.WxSendMessageService;

import java.util.Map;
import java.util.TreeMap;

public class WxSendMessageServiceImpl implements WxSendMessageService {

    @Override
    public String wxSpSendMessageTemplate(WxSpSendMessageTemplate wxSpSendMessageTemplate, String accessToken) {
        String json = JSONObject.toJSONString(wxSpSendMessageTemplate);
        return HttpClientUtil.doPost(String.format(WxRequest.SP_SEND_TEMPLATE_MESSAGE_URL, accessToken), json);
    }

    @Override
    public String wxPpSendMessageTemplate(WxPpSendMessageTemplate wxPpSendMessageTemplate, String accessToken) {
        String json = JSONObject.toJSONString(wxPpSendMessageTemplate);
        return HttpClientUtil.doPost(String.format(WxRequest.PP_SEND_TEMPLATE_MESSAGE_URL, accessToken), json);
    }

}
