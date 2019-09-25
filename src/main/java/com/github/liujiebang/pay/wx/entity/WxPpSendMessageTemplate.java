package com.github.liujiebang.pay.wx.entity;

import java.io.Serializable;
import java.util.Map;

public class WxPpSendMessageTemplate implements Serializable {

    /**
     * 接收者openid
     */
    private String touser;

    /**
     * 模板ID
     */
    private String template_id;

    /**
     * 模板跳转链接
     */
    private String url;

    /**
     * 跳小程序所需数据，不需跳小程序可不用传该数据
     */
    private WxMessageMiniProgram miniprogram;

    /**
     * 模板内容
     */
    private Map<String, WxMessageData> data;

    public WxPpSendMessageTemplate(String touser, String template_id, String url, WxMessageMiniProgram miniprogram, Map<String, WxMessageData> data) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
        this.miniprogram = miniprogram;
        this.data = data;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WxMessageMiniProgram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(WxMessageMiniProgram miniprogram) {
        this.miniprogram = miniprogram;
    }

    public Map<String, WxMessageData> getData() {
        return data;
    }

    public void setData(Map<String, WxMessageData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WxPpSendMessageTemplate{" +
                "touser='" + touser + '\'' +
                ", template_id='" + template_id + '\'' +
                ", url='" + url + '\'' +
                ", miniprogram=" + miniprogram +
                ", data=" + data +
                '}';
    }
}
