package com.alipay.request;

import java.net.URLEncoder;

import com.alipay.enums.AliPayChannelEnum;

/**
 * alipay.trade.app.pay (app支付接口2.0) 请求内容
 */
public class BizContentRequest {
	/**
	 * 该笔订单允许的最晚付款时间，逾期将关闭交易.m-分钟,默认90m（最大长度6）
	 */
	private String timeout_express;
	/**
	 * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]（最大长度9）
	 */
	private String total_amount;
	/**
	 * 销售产品码，商家和支付宝签约的产品码, 固定值QUICK_MSECURITY_PAY（最大长度64）
	 */
	private String product_code;
	/**
	 * 对一笔交易的具体描述信息。 如果是多种商品，请将商品描述字符串累加传给body。（最大长度128）
	 */
	private String body;
	/**
	 * 商品的标题/交易标题/订单标题/订单关键字等。（最大长度256）
	 */
	private String subject;
	/**
	 * 商户网站唯一订单号（最大长度64）
	 */
	private String out_trade_no;
	/**
	 * 绝对超时时间，格式为yyyy-MM-dd HH:mm（最大长度32）
	 */
	private String time_expire;
	/**
	 * 商品主类型 :0-虚拟类商品,1-实物类商品（最大长度2）
	 */
	private String goods_type;
	/**
	 * 优惠参数 注：仅与支付宝协商后可用{"storeIdType":"1"}（最大长度512）
	 */
	private String promo_params;
	/**
	 * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。 支付宝只会在同步返回（包括跳转回商户网站）和异步通知时将该参数原样返回。
	 * 本参数必须进行UrlEncode之后才可以发送给支付宝。（最大长度512）
	 * 如：merchantBizType%3d3C%26merchantBizNo%3d2016010101111
	 */
	private String passback_params;
	/**
	 * 业务扩展参数（这里不做分期业务扩展，如有需求，记得打开，并传入扩展参数）
	 */
	// private ExtendParams extend_params;
	/**
	 * 商户原始订单号，（最大长度32）
	 */
	private String merchant_order_no;
	/**
	 * 可用渠道，用户只能在指定渠道范围内支付 当有多个渠道时用“,”分隔 . 注，与disable_pay_channels互斥（最大长度128）
	 * pcredit,moneyFund,debitCardExpress
	 */
	private String enable_pay_channels;
	/**
	 * 商户门店编号
	 */
	// private String store_id;
	/**
	 * 指定渠道，目前仅支持传入pcredit 若由于用户原因渠道不可用，用户可选择是否用其他渠道支付。
	 * 注：该参数不可与（extend_params）花呗分期参数同时传入（最大长度128） （暂时关闭）
	 */
	// private String specified_channel;
	/**
	 * 禁用渠道，用户不可用指定渠道支付 当有多个渠道时用“,”分隔 注，与enable_pay_channels互斥（最大长度128）
	 * （所以这里不使用了）（暂时关闭）
	 */
	// private String disable_pay_channels;
	/**
	 * 订单包含的商品列表信息，json格式，其它说明详见商品明细说明 （如果有类似购物车等多产品功能可开启使用，暂时关闭）
	 */
	// private GoodsDetail[] goods_detail;
	/**
	 * 外部指定买家（含检验用户信息参数） （暂时关闭）
	 */
	// private ExtUserInfo ext_user_info;
	/**
	 * 商户传入业务信息，具体值要和支付宝约定，应用于安全，营销等参数直传场景，格式为json格式（最大长度512） （暂时关闭）
	 */
	// private String business_params;

	/**
	 * 签约参数。如果希望在sdk中支付并签约，需要在这里传入签约信息。 （暂时关闭）
	 */
	// private SignParams agreement_sign_params;

	public String getTimeout_express() {
		return timeout_express;
	}
	
	@SuppressWarnings("deprecation")
	public BizContentRequest(Integer timeout_express, String total_amount, String body, String subject,
			String out_trade_no, String time_expire, String goods_type, String passback_params,
			String merchant_order_no) {
		super();
		this.timeout_express = timeout_express + "m";
		this.total_amount = total_amount;
		this.product_code = "QUICK_MSECURITY_PAY";
		this.body = body;
		this.subject = subject;
		this.out_trade_no = out_trade_no;
		this.time_expire = time_expire;
		this.goods_type = goods_type;
		this.promo_params = "{\"storeIdType\":\"1\"}";
		this.passback_params = URLEncoder.encode(passback_params);
		this.merchant_order_no = merchant_order_no;
		this.enable_pay_channels = new StringBuffer(AliPayChannelEnum.balance.value()).append(",")
				.append(AliPayChannelEnum.moneyFund.value()).append(",")
				.append(AliPayChannelEnum.creditCardExpress.value()).append(",")
				.append(AliPayChannelEnum.creditCardCartoon.value()).append(",")
				.append(AliPayChannelEnum.debitCardExpress.value()).append(",")
				.append(AliPayChannelEnum.bankPay.value()).toString();
	}

	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}

	public String getPromo_params() {
		return promo_params;
	}

	public void setPromo_params(String promo_params) {
		this.promo_params = promo_params;
	}

	public String getPassback_params() {
		return passback_params;
	}

	public void setPassback_params(String passback_params) {
		this.passback_params = passback_params;
	}

	public String getMerchant_order_no() {
		return merchant_order_no;
	}

	public void setMerchant_order_no(String merchant_order_no) {
		this.merchant_order_no = merchant_order_no;
	}

	public String getEnable_pay_channels() {
		return enable_pay_channels;
	}

	public void setEnable_pay_channels(String enable_pay_channels) {
		this.enable_pay_channels = enable_pay_channels;
	}

}
