package com.github.liujiebang.pay.wx.entity;

import java.io.Serializable;
import java.util.Arrays;

public class WxUnionUserInfo implements Serializable {

    private static final long serialVersionUID = 1l;
    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
     */
    private Integer subscribe;
    /**
     * 授权用户唯一标识
     */
    private String openid;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户性别:0为未知，1为男性，2为女性
     */
    private Integer sex;
    /**
     * 省
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 国家，如中国为CN
     */
    private String country;
    /**
     * 语言
     */
    private String language;
    /**
     * 用户头像
     */
    private String headimgurl;
    /**
     * 微信统一标识
     */
    private String unionid;
    /**
     * 公众号运营者对粉丝的备注
     */
    private String remark;
    /**
     * 用户关注公众号的时间
     */
    private Long subscribe_time;
    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    private Integer groupid;
    /**
     * 用户被打上的标签ID列表
     */
    private Integer[] tagid_list;
    /**
     * 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    private String subscribe_scene;
    /**
     * 二维码扫码场景（开发者自定义）
     */
    private String qr_scene;
    /**
     * 二维码扫码场景描述（开发者自定义）
     */
    private String qr_scene_str;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(Long subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer[] getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(Integer[] tagid_list) {
        this.tagid_list = tagid_list;
    }

    public String getSubscribe_scene() {
        return subscribe_scene;
    }

    public void setSubscribe_scene(String subscribe_scene) {
        this.subscribe_scene = subscribe_scene;
    }

    public String getQr_scene() {
        return qr_scene;
    }

    public void setQr_scene(String qr_scene) {
        this.qr_scene = qr_scene;
    }

    public String getQr_scene_str() {
        return qr_scene_str;
    }

    public void setQr_scene_str(String qr_scene_str) {
        this.qr_scene_str = qr_scene_str;
    }

    @Override
    public String toString() {
        return "WxUnionUserInfo{" +
                "subscribe=" + subscribe +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", unionid='" + unionid + '\'' +
                ", remark='" + remark + '\'' +
                ", subscribe_time=" + subscribe_time +
                ", groupid=" + groupid +
                ", tagid_list=" + Arrays.toString(tagid_list) +
                ", subscribe_scene='" + subscribe_scene + '\'' +
                ", qr_scene='" + qr_scene + '\'' +
                ", qr_scene_str='" + qr_scene_str + '\'' +
                '}';
    }
}
