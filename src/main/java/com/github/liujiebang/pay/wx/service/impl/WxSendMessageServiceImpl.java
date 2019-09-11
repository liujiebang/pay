package com.github.liujiebang.pay.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.liujiebang.pay.utils.HttpClientUtil;
import com.github.liujiebang.pay.wx.entity.WxRequest;
import com.github.liujiebang.pay.wx.entity.WxSendMessageTemplate;
import com.github.liujiebang.pay.wx.service.WxSendMessageService;

public class WxSendMessageServiceImpl implements WxSendMessageService {

    @Override
    public String wxSpSendMessageTemplate(WxSendMessageTemplate wxSendMessageTemplate, String accessToken) {
        String json = JSONObject.toJSONString(wxSendMessageTemplate);
        return HttpClientUtil.doPost(String.format(WxRequest.SP_SEND_TEMPLATE_MESSAGE_URL, accessToken), json);
    }

}
