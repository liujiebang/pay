package com.github.liujiebang.pay.wx.entity;


import java.io.Serializable;
import java.util.Map;

public class WxSendMessageTemplate implements Serializable {

    /**
     * 接收者的openid
     */
    private String touser;
    /**
     * 小程序的消息模板id
     */
    private String template_id;
    /**
     * 点击模板卡片要跳转的页面,(如index?id=1),不填则则无法跳转
     */
    private String page;
    /**
     * 表单提交为:formId,支付提交为:prepay_id
     */
    private String form_id;

    /**
     * 模板内容
     */
    private Map<String, WxMessageData> data;

    /**
     * 模板需要方大的关键词
     */
    private String emphasis_keyword;

    public String getTouser() {
        return touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public String getPage() {
        return page;
    }

    public String getForm_id() {
        return form_id;
    }

    public Map<String, WxMessageData> getData() {
        return data;
    }

    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public void setData(Map<String, WxMessageData> data) {
        this.data = data;
    }

    public void setEmphasis_keyword(String emphasis_keyword) {
        this.emphasis_keyword = emphasis_keyword;
    }

    @Override
    public String toString() {
        return "WxSendMessageTemplate{" +
                "touser='" + touser + '\'' +
                ", template_id='" + template_id + '\'' +
                ", page='" + page + '\'' +
                ", form_id='" + form_id + '\'' +
                ", data=" + data +
                ", emphasis_keyword='" + emphasis_keyword + '\'' +
                '}';
    }
}
