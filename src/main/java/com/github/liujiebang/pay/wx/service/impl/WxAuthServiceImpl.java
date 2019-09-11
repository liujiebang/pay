package com.github.liujiebang.pay.wx.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.liujiebang.pay.utils.HttpClientUtil;
import com.github.liujiebang.pay.utils.IdentityUtil;
import com.github.liujiebang.pay.utils.PayException;
import com.github.liujiebang.pay.wx.config.WxConfig;
import com.github.liujiebang.pay.wx.entity.*;
import com.github.liujiebang.pay.wx.service.WxAuthService;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;


public class WxAuthServiceImpl implements WxAuthService {

    @Override
    public String wxPpOAuth2CodeAuthorizationUrl(String redirectUrl, String scope, String state, WxConfig wxConfig) {
        String str = null;
        try {
            checkOAuth2CodeAuthorizationUrl(redirectUrl, scope, state);
            str = String.format(WxRequest.PP_CODE_AUTHORIZATION_URL, wxConfig.getPpAppId(), URLEncoder.encode(redirectUrl, "utf-8"), scope, state);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public String wxOpOAuth2CodeAuthorizationUrl(String redirectUrl, String state,WxConfig wxConfig) {
        String str = null;
        try {
            checkOAuth2CodeAuthorizationUrl(redirectUrl, WxRequest.oAuth2Scope.SNSAPI_LOGIN, state);
            str = String.format(WxRequest.OP_CODE_AUTHORIZATION_URL, wxConfig.getOpAppId(), URLEncoder.encode(redirectUrl, "utf-8"), WxRequest.oAuth2Scope.SNSAPI_LOGIN, state);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public WxOAuth2Info wxOpOAuth2AccessToken(String code,WxConfig wxConfig) throws Exception {
        String url = String.format(WxRequest.UNIFIED_ACCESSTOKEN_URL, wxConfig.getOpAppId(), wxConfig.getOpSecrect(), code);
        return getWxOAuth2Info(url);
    }

    @Override
    public WxOAuth2Info wxPpOAuth2AccessToken(String code,WxConfig wxConfig) throws Exception {
        String url = String.format(WxRequest.UNIFIED_ACCESSTOKEN_URL, wxConfig.getPpAppId(), wxConfig.getPpSecrect(), code);
        return getWxOAuth2Info(url);
    }

    @Override
    public WxOAuth2Info wxSpOAuth2AccessToken(String code,WxConfig wxConfig) throws Exception {
        String url = null;
        if (code == null || "".equals(code)) {
            url = String.format(WxRequest.SP_GET_ACCESSTOKEN_URL, wxConfig.getSpAppId(), wxConfig.getSpSecrect());
        } else {
            url = String.format(WxRequest.SP_ACCESSTOKEN_URL, wxConfig.getSpAppId(), wxConfig.getSpSecrect(), code);
        }
        return getWxOAuth2Info(url);
    }

    @Override
    public WxUserInfo wxOAuth2getUserInfo(String accessToken, String openId) throws Exception {
        String url = String.format(WxRequest.UNIFIED_OAUTH2_USERINFO_URL, accessToken, openId);
        return getWxUserInfo(url);
    }

    @Override
    public WxOAuth2Info wxPpOAuth2ScanAccessToken(WxConfig wxConfig) throws Exception {
        String url = String.format(WxRequest.PP_SCAN_ACCESSTOKEN_URL, wxConfig.getPpAppId(), wxConfig.getPpSecrect());
        return getWxOAuth2Info(url);
    }

    @Override
    public WxScanSign wxPpOAuth2ScanSign(String url, String accessToken,WxConfig wxConfig) throws Exception {
        String requestUrl = String.format(WxRequest.PP_SCAN_JSAPITICKET_URL, accessToken);
        String json = HttpClientUtil.doGet(requestUrl);
        Map<String, String> sdkMap = JSON.parseObject(json, Map.class);
        String ticket = sdkMap.get("ticket");
        return scanSign(url, ticket,wxConfig);
    }

    @Override
    public WxUnionUserInfo wxPpGetUnionUserInfo(String accessToken, String openId) throws Exception {
        String url = String.format(WxRequest.PP_GET_UNION_USERINFO_URL, accessToken, openId);
        String json = HttpClientUtil.doGet(url);
        if (json.toLowerCase().contains(WxRequest.error.err_code)) {
            throw new PayException(json);
        }
        return JSON.parseObject(json, WxUnionUserInfo.class);
    }

    private void checkOAuth2CodeAuthorizationUrl(String redirectUrl, String scope, String state) throws PayException {
        if (redirectUrl == null || "".equals(redirectUrl)) {
            throw new PayException("重定向路径不能为空");
        }
        if (state == null || "".equals(state)) {
            state = "state";
        }
        System.out.println(scope);
        if (!WxRequest.oAuth2Scope.SNSAPI_BASE.equals(scope) && !WxRequest.oAuth2Scope.SNSAPI_USERINFO.equals(scope) && !WxRequest.oAuth2Scope.SNSAPI_LOGIN.equals(scope)) {
            throw new PayException("scope为空或错误");
        }
    }

    private WxOAuth2Info getWxOAuth2Info(String url) throws Exception {
        String json = HttpClientUtil.doGet(url);
        if (json.toLowerCase().contains(WxRequest.error.err_code)) {
            throw new PayException(json);
        }
        return JSON.parseObject(json, WxOAuth2Info.class);
    }

    private WxUserInfo getWxUserInfo(String url) throws Exception {
        String json = HttpClientUtil.doGet(url);
        if (json.toLowerCase().contains(WxRequest.error.err_code)) {
            throw new PayException(json);
        }
        return JSON.parseObject(json, WxUserInfo.class);
    }

    private WxScanSign scanSign(String url, String ticket,WxConfig wxConfig) {
        String nonce_str = IdentityUtil.uuid();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        StringBuffer sb = new StringBuffer();
        sb.append("jsapi_ticket=" + ticket);
        sb.append("&noncestr=" + nonce_str);
        sb.append("&timestamp=" + timestamp);
        sb.append("&url=" + url);
        String signature = IdentityUtil.shaSign(sb.toString());
        WxScanSign wxScanSign = new WxScanSign();
        wxScanSign.setAppId(wxConfig.getPpAppId());
        wxScanSign.setNonceStr(nonce_str);
        wxScanSign.setTimestamp(timestamp);
        wxScanSign.setSignature(signature);
        return wxScanSign;
    }

}
