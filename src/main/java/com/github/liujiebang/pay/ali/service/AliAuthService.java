package com.github.liujiebang.pay.ali.service;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;

public interface AliAuthService extends AliService{

    /**
     * 生活号授权获取code
     *
     * @param redirectUrl 授权后重定向链接
     * @param scope       接口权限值:auth_user 或 auth_base
     * @param state       附带参数，重定向会带上该参数
     * @return 授权重定向链接
     */
    String publicAppAuthorize(String redirectUrl, String scope, String state);

    /**
     * 获取token userId
     *
     * @param authCode     临时授权凭证
     * @param refreshToken 刷新令牌。通过该令牌可以刷新access_token
     * @return 返回授权令牌，用户ID等
     */
    AlipaySystemOauthTokenResponse systemOauthToken(String authCode, String refreshToken);

    /**
     * 获取用户信息
     *
     * @param accessToken 交换令牌
     * @return 返回支付宝用户基本信息
     */
    AlipayUserInfoShareResponse getUserInfoShare(String accessToken);
}
