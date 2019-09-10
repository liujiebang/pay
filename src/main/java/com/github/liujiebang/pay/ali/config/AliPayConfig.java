package com.github.liujiebang.pay.ali.config;


public class AliPayConfig extends AliConfig {

    private static final long serialVersionUID = 1l;

    /**
     * 支付宝appId
     */
    private String appId;
    /**
     * 应用秘钥
     */
    private String privateKey;
    /**
     * 应用公钥
     */
    private String publicKey;
    /**
     * 支付宝公钥
     */
    private String aliPublicKey;
    /**
     * 支付回调地址
     */
    private String notifyUrl;
    /**
     * 账号
     */
    private String sellerId;
    /**
     * 商户号的appId
     */
    private String payeeUserId;
    /**
     * 重定向地址
     */
    private String returnUrl;
    /**
     * 失败地址
     */
    private String quitUrl;

    /**
     * 资金授权回调地址
     */
    private String fundAuthNotifyUrl;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getAliPublicKey() {
        return aliPublicKey;
    }

    public void setAliPublicKey(String aliPublicKey) {
        this.aliPublicKey = aliPublicKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getQuitUrl() {
        return quitUrl;
    }

    public void setQuitUrl(String quitUrl) {
        this.quitUrl = quitUrl;
    }

    public String getPayeeUserId() {
        return payeeUserId;
    }

    public void setPayeeUserId(String payeeUserId) {
        this.payeeUserId = payeeUserId;
    }

    public String getFundAuthNotifyUrl() {
        return fundAuthNotifyUrl;
    }

    public void setFundAuthNotifyUrl(String fundAuthNotifyUrl) {
        this.fundAuthNotifyUrl = fundAuthNotifyUrl;
    }

    @Override
    public String toString() {
        return "AliPayConfig{" +
                "appId='" + appId + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", aliPublicKey='" + aliPublicKey + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", payeeUserId='" + payeeUserId + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", quitUrl='" + quitUrl + '\'' +
                ", fundAuthNotifyUrl='" + fundAuthNotifyUrl + '\'' +
                '}';
    }
}
