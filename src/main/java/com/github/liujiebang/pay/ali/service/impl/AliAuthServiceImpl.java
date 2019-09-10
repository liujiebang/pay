package com.github.liujiebang.pay.ali.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.github.liujiebang.pay.ali.entity.AliRequest;
import com.github.liujiebang.pay.ali.service.AliAuthService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AliAuthServiceImpl extends AliServiceImpl implements AliAuthService {

    @Override
    public String publicAppAuthorize(String redirectUrl, String scope, String state) {
        String url = null;
        try {
            url = String.format(AliRequest.PUBLIC_APP_AUTHORIZE_URL, aliPayConfig.getAppId(), scope, URLEncoder.encode(redirectUrl, "utf-8"), state);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public AlipaySystemOauthTokenResponse systemOauthToken(String authCode, String refreshToken) {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        if (authCode != null && !"".equals(authCode.trim())) {
            request.setCode(authCode);
            request.setGrantType(AliRequest.GrantType.AUTHORIZATION_CODE);
        } else if (refreshToken != null && !"".equals(refreshToken.trim())) {
            request.setRefreshToken(refreshToken);
            request.setGrantType(AliRequest.GrantType.REFRESH_TOKEN);
        }
        try {
            AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
            return response;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public AlipayUserInfoShareResponse getUserInfoShare(String accessToken) {
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        try {
            AlipayUserInfoShareResponse response = alipayClient.execute(request, accessToken);
            if (response.isSuccess()) {
                return response;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

}
