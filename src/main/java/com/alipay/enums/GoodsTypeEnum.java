package com.alipay.enums;

/**
 * 商品主类型
 */
public enum GoodsTypeEnum {

	VIRTUAL("0","虚拟类商品"),
	ENTITY("1","实物类商品");

	private String value;
	private String alias;

	private GoodsTypeEnum(String value, String alias) {
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
