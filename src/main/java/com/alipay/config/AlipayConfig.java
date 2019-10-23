package com.alipay.config;

/**
 * 类名：AlipayConfig 
 * 功能：基础配置类 
 * 详细：设置帐户有关信息及返回路径
 */
public class AlipayConfig {

	// 设置网关地址
	public static String server_url = "https://openapi.alipay.com/gateway.do";
	// 设置应用Id
	public static String app_id = "";
	// 置应用私钥
	public static String private_key = "";
	// 设置请求格式，固定值json
	public static String format = "json";
	// 设置字符集
	public static String charset = "utf-8";
	// 设置签名类型
	public static String sign_type = "RSA2";
	// 设置商户号,多个可用英文逗号隔开
	public static String seller_id = "";

}
