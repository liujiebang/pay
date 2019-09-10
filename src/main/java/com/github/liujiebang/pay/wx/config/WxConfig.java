package com.github.liujiebang.pay.wx.config;

import java.io.Serializable;

public class WxConfig implements Serializable {

    private static final long serialVersionUID = 1l;
    /**
     * 商品
     */
    private String body = "深圳维码物联";

    /**
     * 开放平台appId
     */
    private String opAppId;

    /**
     * 开放平台关联商户号
     */
    private String opMchId;

    /**
     * 开放平台商户秘钥
     */
    private String opMchKey;

    /**
     * 开放平台应用秘钥
     */
    private String opSecrect;

    /**
     * 开放平台商户号证书地址
     */
    private String opCertPath;


    /**
     * 公众平台appId
     */
    private String ppAppId;

    /**
     * 公众平台关联商户号
     */
    private String ppMchId;

    /**
     * 公众平台商户秘钥
     */
    private String ppMchKey;

    /**
     * 公众号应用秘钥
     */
    private String ppSecrect;

    /**
     * 公众平台关联商户证书地址
     */
    private String ppCertPath;


    /**
     * 微信小程序appId
     */
    private String spAppId;

    /**
     * 小程序应用秘钥
     */
    private String spSecrect;

    /**
     * 小程序关联商户号
     */
    private String spMchId;

    /**
     * 小程序关联商户秘钥
     */
    private String spMchKey;

    /**
     * 小程序关联商户证书地址
     */
    private String spCertPath;

    /**
     * 统一异步回调地址
     */
    private String notifyUrl;


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOpAppId() {
        return opAppId;
    }

    public void setOpAppId(String opAppId) {
        this.opAppId = opAppId;
    }

    public String getOpMchId() {
        return opMchId;
    }

    public void setOpMchId(String opMchId) {
        this.opMchId = opMchId;
    }

    public String getOpMchKey() {
        return opMchKey;
    }

    public void setOpMchKey(String opMchKey) {
        this.opMchKey = opMchKey;
    }

    public String getOpSecrect() {
        return opSecrect;
    }

    public void setOpSecrect(String opSecrect) {
        this.opSecrect = opSecrect;
    }

    public String getOpCertPath() {
        return opCertPath;
    }

    public void setOpCertPath(String opCertPath) {
        this.opCertPath = opCertPath;
    }

    public String getPpAppId() {
        return ppAppId;
    }

    public void setPpAppId(String ppAppId) {
        this.ppAppId = ppAppId;
    }

    public String getPpMchId() {
        return ppMchId;
    }

    public void setPpMchId(String ppMchId) {
        this.ppMchId = ppMchId;
    }

    public String getPpMchKey() {
        return ppMchKey;
    }

    public void setPpMchKey(String ppMchKey) {
        this.ppMchKey = ppMchKey;
    }

    public String getPpSecrect() {
        return ppSecrect;
    }

    public void setPpSecrect(String ppSecrect) {
        this.ppSecrect = ppSecrect;
    }

    public String getPpCertPath() {
        return ppCertPath;
    }

    public void setPpCertPath(String ppCertPath) {
        this.ppCertPath = ppCertPath;
    }

    public String getSpAppId() {
        return spAppId;
    }

    public void setSpAppId(String spAppId) {
        this.spAppId = spAppId;
    }

    public String getSpSecrect() {
        return spSecrect;
    }

    public void setSpSecrect(String spSecrect) {
        this.spSecrect = spSecrect;
    }

    public String getSpMchId() {
        return spMchId;
    }

    public void setSpMchId(String spMchId) {
        this.spMchId = spMchId;
    }

    public String getSpMchKey() {
        return spMchKey;
    }

    public void setSpMchKey(String spMchKey) {
        this.spMchKey = spMchKey;
    }

    public String getSpCertPath() {
        return spCertPath;
    }

    public void setSpCertPath(String spCertPath) {
        this.spCertPath = spCertPath;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String toString() {
        return "WxConfig{" +
                "body='" + body + '\'' +
                ", opAppId='" + opAppId + '\'' +
                ", opMchId='" + opMchId + '\'' +
                ", opMchKey='" + opMchKey + '\'' +
                ", opSecrect='" + opSecrect + '\'' +
                ", opCertPath='" + opCertPath + '\'' +
                ", ppAppId='" + ppAppId + '\'' +
                ", ppMchId='" + ppMchId + '\'' +
                ", ppMchKey='" + ppMchKey + '\'' +
                ", ppSecrect='" + ppSecrect + '\'' +
                ", ppCertPath='" + ppCertPath + '\'' +
                ", spAppId='" + spAppId + '\'' +
                ", spSecrect='" + spSecrect + '\'' +
                ", spMchId='" + spMchId + '\'' +
                ", spMchKey='" + spMchKey + '\'' +
                ", spCertPath='" + spCertPath + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                '}';
    }
}
