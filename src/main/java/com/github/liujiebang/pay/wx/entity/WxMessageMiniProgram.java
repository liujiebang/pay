package com.github.liujiebang.pay.wx.entity;


public class WxMessageMiniProgram {

    /**
     * 所需跳转到的小程序appid
     */
    private String appid;

    /**
     * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏
     */
    private String pagepath;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }



    public WxMessageMiniProgram(String appid, String pagepath) {
        this.appid = appid;
        this.pagepath = pagepath;
    }
}
