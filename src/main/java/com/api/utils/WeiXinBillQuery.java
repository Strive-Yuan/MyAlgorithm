package com.api.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("xml")
public class WeiXinBillQuery {
    @JsonProperty("appid")
    public String appid; //微信分配的公众账户id
    @JsonProperty("mch_id")
    public String mchId; //微信支付分配的商户号
    @JsonProperty("nonce_str")
    public String nonceStr; //随机字符串,不长于32位,推荐使用随机数字生成算法
    @JsonProperty("sign")
    public String sign; //签名
    @JsonProperty("sign_type")
    public String signType; //签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
    @JsonProperty("bill_date")
    public String billDate; //对账单日期:下载对账单的日期，格式：20140603
    @JsonProperty("bill_type")
    //账单类型:ALL（默认值），返回当日所有订单信息（不含充值退款订单）SUCCESS，返回当日成功支付的订单（不含充值退款订单）REFUND，
    //返回当日退款订单（不含充值退款订单）RECHARGE_REFUND，返回当日充值退款订单
    public String billType;
    @JsonProperty("tar_type")
    public String tarType; //压缩账单:非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
    @JsonProperty("body")
    public String body;
}
