package com.github.liujiebang.pay.wx.service;

import com.github.liujiebang.pay.wx.entity.WxOAuth2Info;
import com.github.liujiebang.pay.wx.entity.WxScanSign;
import com.github.liujiebang.pay.wx.entity.WxUnionUserInfo;
import com.github.liujiebang.pay.wx.entity.WxUserInfo;


public interface WxAuthService extends WxService {

    /**
     * 公众号授权获取code
     *
     * @param redirectUrl 授权后重定向链接
     * @param scope       应用作用域
     * @param state       重定向后会带上的参数（最多128字节）
     * @return 微信客户端链接
     */
    String wxPpOAuth2CodeAuthorizationUrl(String redirectUrl, String scope, String state);

    /**
     * 微信开放平台获取code
     *
     * @param redirectUrl 授权后重定向链接
     * @param state       重定向后会带上的参数（最多128字节）
     * @return 微信网页链接
     */
    String wxOpOAuth2CodeAuthorizationUrl(String redirectUrl, String state);

    /**
     * 微信开放平台获取AccessToken
     *
     * @param code 微信临时票据
     * @return 返回微信授权的access_token, openid等
     * @throws Exception
     */
    WxOAuth2Info wxOpOAuth2AccessToken(String code) throws Exception;

    /**
     * 微信公众平台获取AccessToken
     *
     * @param code 微信临时票据
     * @return 返回微信授权的access_token, openid等, 当且网站应用已获取该用户的userinfo授权时, 会返回unionid
     * @throws Exception
     */
    WxOAuth2Info wxPpOAuth2AccessToken(String code) throws Exception;

    /**
     * 微信小程序获取AccessToken
     *
     * @param code 微信临时票据
     * @return
     * @throws Exception
     */
    WxOAuth2Info wxSpOAuth2AccessToken(String code) throws Exception;

    /**
     * 微信获取用户信息
     *
     * @param accessToken 接口调用凭证
     * @param openId      授权用户唯一标识
     * @return
     * @throws Exception
     */
    WxUserInfo wxOAuth2getUserInfo(String accessToken, String openId) throws Exception;

    /**
     * 微信公众号扫一扫获取AccessToken
     *
     * @return 接口调用凭证
     * @throws Exception
     */
    WxOAuth2Info wxPpOAuth2ScanAccessToken() throws Exception;

    /**
     * 微信公众号生成扫一扫的签名
     *
     * @param url         调起扫一扫的链接
     * @param accessToken 接口调用凭证
     * @return 返回扫一扫签名
     * @throws Exception
     */
    WxScanSign wxPpOAuth2ScanSign(String url, String accessToken) throws Exception;

    /**
     * 获取用户基本信息（包括UnionID机制）
     *
     * @param accessToken 接口调用凭证
     * @param openId      授权用户唯一标识
     * @return
     * @throws Exception
     */
    WxUnionUserInfo wxPpGetUnionUserInfo(String accessToken, String openId) throws Exception;

}
