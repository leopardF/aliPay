package com.alipay.enums;

/**
 * 渠道说明
 * http://www.360doc.com/content/18/0330/17/40412629_741579275.shtml
 * https://docs.open.alipay.com/204/105301/
 */
public enum AliPayChannelEnum {

	ALIPAYACCOUNT("ALIPAYACCOUNT","支付宝余额"),
	FINANCEACCOUNT("FINANCEACCOUNT","余额宝"),
	COUPON("COUPON","支付宝红包"),
	POINT("POINT","集分宝"),
	DISCOUNT("DISCOUNT","折扣券"),
	PCARD("PCARD","预付卡"),
	MCARD("MCARD","商家储值卡"),
	PCREDIT("PCREDIT","蚂蚁花呗"),
	MDISCOUNT("MDISCOUNT","商户优惠券"),
	MCOUPON("MCOUPON","商户红包");
	

	private String value;
	private String alias;

	private AliPayChannelEnum(String value, String alias) {
		this.value = value;
		this.alias = alias;
	}

	public String value() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String alias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
