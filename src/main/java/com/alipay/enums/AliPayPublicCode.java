package com.alipay.enums;

/**
 * 公共状态码
 */
public enum AliPayPublicCode {

	Success("10000","接口调用成功"),
	Business_Failed("40004","业务处理失败");
	

	private String code;
	private String alias;

	private AliPayPublicCode(String code, String alias) {
		this.code = code;
		this.alias = alias;
	}

	public String code() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String alias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
}
