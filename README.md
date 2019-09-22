## 前言
在公司做了很多移动端项目，如今微信支付宝公众号，小程序又特别火爆，免不了要接触支付，授权这类的业务需求,所以在工作之余对支付宝支付，微信支付与授权重新梳理，独立出来分享到maven中央仓库，方便大家简单使用！
## 微信支付
支付宝相关API就比较简单了,我这边对应支付宝官方文档对appid,公钥秘钥做了简单封装，基本步骤和微信支付一样，就不贴代码了。

微信支付这边，当时封装为了方便就按心情起了别名，这边稍微解释一下，后面的其他API调用就大致清晰明了了,每个平台的参数初始化配置都需要设置相应商户号和商户秘钥,因为可能有的客户可能开放平台绑定一个商户号，公众平台绑定一个商户号，所有这边就要一一区分开来。

>前缀wxOp--open platform 开放平台
>前缀wxPp--public platform 公众平台
>前缀wxSp--small program 小程序

**整个调用过程也比较简单，java后台就3步操作:**

 >1.初始化参数	 
 >2.生成统一下单预支付参数
```javascript

    public static void main(String[] args) {
        WxConfig wxConfig=new WxConfig();
        wxConfig.setNotifyUrl("微信异步通知回调地址");
        //开发平台
        wxConfig.setWxOpAppId("开放平台APPID");
        wxConfig.setWxOpSecrect("开放平台秘钥");
        wxConfig.setWxOpMchId("商户号");
        wxConfig.setWxOpMchKey("商户秘钥");
        wxConfig.setWxOpCertPath("证书地址路径【退款,企业转账功能需要该参数】");
        //公众平台
        wxConfig.setWxPpAppId("公众平台APPID");
        wxConfig.setWxPpSecrect("公众平台秘钥");
        wxConfig.setWxPpMchId("商户号");
        wxConfig.setWxPpMchKey("商户秘钥");
        wxConfig.setWxPpCertPath("证书地址路径【退款,企业转账功能需要该参数】");
        //小程序
        wxConfig.setWxSpAppId("小程序APPID");
        wxConfig.setWxSpSecrect("小程序");
        wxConfig.setWxSpMchId("商户号");
        wxConfig.setWxSpMchKey("商户秘钥");
        wxConfig.setWxSpCertPath("证书地址路径【退款,企业转账功能需要该参数】");
        WxPayService wxPayService=new WxPayServiceImpl();
        wxPayService.setWxConfigStorage(wxConfig);
	//网页支付NATIVE模式返回支付二维码链接,用于生成二维码进行扫码支付
        System.out.println(wxPayService.wxWebPay("支付单号",0.01));		
	System.out.println(wxPayService.wxAppPay("支付单号",0.01));
        System.out.println(wxPayService.wxPpPay("支付单号",0.01,"公众号获取的openId"));
        System.out.println(wxPayService.wxSpPay("支付单号",0.01,"小程序获取的openId"));
    }
```
 >3.接收微信异步通知结果
```javascript
    @PostMapping(value = "/wechat/notify")
    public String wechatNotify(HttpServletRequest request) {
    String result ="";
        try {
            Map<String, String> map = XMLUtil.wxPayNotify(request);
            if ("SUCCESS".equals(map.get("return_code")) && "SUCCESS".equals(map.get("result_code"))) {
   		//签名校验-取绑定对应平台的商户秘钥
                if (IdentityUtil.inspectionSign(map, wxConfig.getWxSpMchKey())) {
                    //调起支付所传入的支付流水号
                    String outTradeNo = map.get("out_trade_no");
                    //业务逻辑处理--校验签名,支付单号,还可以校验金额这里自由发挥
                    result = XMLUtil.setWechatXml("SUCCESS", "OK");
                } else {
                    return XMLUtil.setWechatXml("FAIL", "验签失败");
                }  
            }
        } catch (Exception e) {
	    result = XMLUtil.setWechatXml("FAIL", "回调通知异常");
	    log.info("---------------------------回调通知异常！！！-------------------------------");
            e.printStackTrace();
        } finally {
            return result;
        }
    }

```


## 微信授权
授权这边主要调用WxAuthService接口

>也是对应别名区分不同平台 参数的初始化和微信支付一样，只需要传入对应平台的appid和secrect，不需要商户号的配置信息--开发业务流程需要对照微信开发文档业务流程，这边我就不一一阐述了。

[微信公众平台技术文档](https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842)

```javascript

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


```

## maven依赖(更新)
后续会持续更新。（觉得好用可以点个赞哟）
```javascript
		
        <dependency>
            <groupId>com.github.liujiebang</groupId>
            <artifactId>pay</artifactId>
            <version>1.5.8</version>
        </dependency>
        
```
        

