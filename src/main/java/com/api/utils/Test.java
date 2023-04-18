package com.api.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Test {
    @JsonProperty("appid")
    public String appid = "wxd930ea5d5a258f4f"; //微信分配的公众账户id
    @JsonProperty("mch_id")
    public String mchId = "10000100"; //微信支付分配的商户号
    @JsonProperty("device_info")
    public String deviceInfo = "1000";
    @JsonProperty("body")
    public String body = "test";
    @JsonProperty("nonce_str")
    public String nonceStr = "ibuaiVcKdpRxkhJA";
}
