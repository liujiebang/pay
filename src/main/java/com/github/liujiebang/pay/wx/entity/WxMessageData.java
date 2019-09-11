package com.github.liujiebang.pay.wx.entity;

public class WxMessageData {

    private String value;

    private String color;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public WxMessageData(String value) {
        this.value = value;
    }
    public WxMessageData(String value, String color) {
        this.value = value;
        this.color = color;
    }
}
