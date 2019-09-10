package com.github.liujiebang.pay.ali.entity;


public class AliRequest {

    public static final String PUBLIC_APP_AUTHORIZE_URL="https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=%s&scope=%s&redirect_uri=%s&state=%s";

    public static class ScopeType{
        public static final String AUTH_USER = "auth_user";
        public static final String AUTH_BASE = "auth_base";
    }

    public static class GrantType{
        public static final String AUTHORIZATION_CODE = "authorization_code";
        public static final String REFRESH_TOKEN = "refresh_token";
    }
}
