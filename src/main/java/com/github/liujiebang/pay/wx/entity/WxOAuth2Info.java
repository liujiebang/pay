package com.github.liujiebang.pay.wx.entity;

import java.io.Serializable;

public class WxOAuth2Info implements Serializable {

    private static final long serialVersionUID = 1l;

    /**
     * 接口调用凭证
     */
    private String access_token;
    /**
     * accrss_token接口调用凭证超时时间（秒）
     */
    private Integer expires_in;
    /**
     * 用于刷新accrss_token
     */
    private String refresh_token;
    /**
     * 授权用户唯一标示
     */
    private String openid;
    /**
     * 用户授权作用域，多个以为","分隔
     */
    private String scope;
    /**
     * 小程序会返回参数
     */
    private String session_key;
    /**
     * 当该网站应用已获取用户的userinfo授权时，才会出现该字段
     * （小程序和开放平台应用会返回）
     */
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    @Override
    public String toString() {
        return "WxOAuth2Info{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", openid='" + openid + '\'' +
                ", scope='" + scope + '\'' +
                ", session_key='" + session_key + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}
