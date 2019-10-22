package com.alipay.enums;

/**
 * APP支付异常响应状态码40004
 */
public enum AliAppPayResponseCode {

	SUCCESS("success","data","将回调参数放入data内"),
	
	ERROR("ERROR","请求有误，请重新提交","非业务错误码范围，请查看信息核对"),
	CRT_FILE_ERROR("CRT_FILE_ERROR","证书文件异常，提交失败","请排查路径是否正确，证书是否存在"),
	VERIFY_SIGN_ERROR("VERIFY_SIGN_ERROR","验证签名失败","请通过日志查看解签参数，及验证参数配置"),
	
	SYSTEM_ERROR("ACQ.SYSTEM_ERROR","接口返回错误","请立即调用查询订单API，查询当前订单的状态，并根据订单状态决定下一步的操作"),
	ACCESS_FORBIDDEN("ACQ.ACCESS_FORBIDDEN","无权限使用接口","联系支付宝小二签约"),
	EXIST_FORBIDDEN_WORD("ACQ.EXIST_FORBIDDEN_WORD","订单信息中包含违禁词","修改订单信息后，重新发起请求"),
	PARTNER_ERROR("ACQ.PARTNER_ERROR","应用APP_ID填写错误","联系支付宝小二，确认APP_ID的状态"),
	TOTAL_FEE_EXCEED("ACQ.TOTAL_FEE_EXCEED","订单总金额不在允许范围内","修改订单金额再发起请求"),
	BUYER_SELLER_EQUAL("ACQ.BUYER_SELLER_EQUAL","买卖家不能相同","更换买家重新付款"),
	BUYER_ENABLE_STATUS_FOR("ACQ.BUYER_ENABLE_STATUS_FORBID","买家状态非法","用户联系支付宝小二，确认买家状态为什么非法"),
	SELLER_BEEN_BLOCKED("ACQ.SELLER_BEEN_BLOCKED","商家账号被冻结","联系支付宝小二，解冻账号"),
	INVALID_PARAMETER("ACQ.INVALID_PARAMETER","参数无效","若存在参数无效具体错误信息描述，请参考描述检查请求参数后，重新发起"),
	TRADE_HAS_CLOSE("ACQ.TRADE_HAS_CLOSE","交易已经关闭","确认该笔交易信息是否为当前买家的已存订单，如果是则认为交易已经关闭，如果不是则更换商家订单号后，重新发起请求"),
	CONTEXT_INCONSISTENT("ACQ.CONTEXT_INCONSISTENT","交易信息被篡改","确认该笔交易信息是否为当前买家的已存订单，如果是则认为本次请求参数与已存交易不一致，如果不是则更换商家订单号后，重新发起请求"),
	TRADE_BUYER_NOT_MATCH("ACQ.TRADE_BUYER_NOT_MATCH","交易买家不匹配","该笔交易已经在支付宝端创建，但请求买家与已存交易中的买家不一致。请商户确认本次请求是否与已存交易有关，若为同一笔交易，则只能用原始买家付款，若无关更换商家订单号后，重新发起请求"),
	TRADE_HAS_SUCCESS("ACQ.TRADE_HAS_SUCCESS","交易已被支付","确认该笔交易信息是否为当前买家的，如果是则认为交易付款成功，如果不是则更换商家订单号后，重新发起请求");
	

	private String code;
	private String alias;
	private String solution;

	private AliAppPayResponseCode(String code, String alias , String solution) {
		this.code = code;
		this.alias = alias;
		this.solution = solution;
	}
	
	public static AliAppPayResponseCode findCode(String code){
		AliAppPayResponseCode[] values = AliAppPayResponseCode.values();
		for (AliAppPayResponseCode aliAppPayResponseCode : values) {
			if(code.equals(aliAppPayResponseCode.code())){
				return aliAppPayResponseCode;
			}
		}
		return AliAppPayResponseCode.ERROR;
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

	public String solution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
}
