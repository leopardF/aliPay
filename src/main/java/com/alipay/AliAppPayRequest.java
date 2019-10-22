package com.alipay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;

import org.apache.commons.logging.impl.Log4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.GetRuleInfo;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.config.AlipayConfig;
import com.alipay.enums.AliAppPayResponseCode;
import com.alipay.request.BizContentRequest;

public class AliAppPayRequest {
	
	private static final Logger logger = LoggerFactory.getLogger(AliAppPayRequest.class);

	/**
	 * 支付请求
	 * 
	 * @param bizContentRequest
	 * @param notifyUrl
	 *            异步回调地址
	 * @return
	 */
	public AliAppPayResponseCode pay(BizContentRequest bizContentRequest, String notifyUrl) {

		if(getCertAlipayRequest() != null){

			return AliAppPayResponseCode.PARAM_ERROR;
		}
		
		if (checkObjFieldIsNull(bizContentRequest)) {
			logger.info("请求参数缺失！");
			return AliAppPayResponseCode.PARAM_ERROR;
		}
		String bizContentJson = JSONObject.toJSONString(bizContentRequest);
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		request.setBizContent(bizContentJson);
		request.setNotifyUrl(notifyUrl);
		request.setApiVersion("2.0");

		// 构造client
		AlipayClient alipayClient = null;
		try {
			alipayClient = new DefaultAlipayClient(getCertAlipayRequest());
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			System.out.println(response.getBody());// 就是orderString
													// 可以直接给客户端请求，无需再做处理。
			if (response.isSuccess()) {
				AliAppPayResponseCode success = AliAppPayResponseCode.SUCCESS;
				success.setAlias(response.getBody());
				return success;
			} else {
				AliAppPayResponseCode errorCode = AliAppPayResponseCode.findCode(response.getSubCode());
				return errorCode;
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return AliAppPayResponseCode.ERROR;
		}
	}

	/**
	 * 构建证书
	 * 
	 * @return
	 */
	private CertAlipayRequest getCertAlipayRequest() {
		// 构造client
		CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
		// 设置网关地址
		certAlipayRequest.setServerUrl(AlipayConfig.server_url);
		// 设置应用Id
		certAlipayRequest.setAppId(AlipayConfig.app_id);
		// 设置应用私钥
		certAlipayRequest.setPrivateKey(AlipayConfig.private_key);
		// 设置请求格式，固定值json
		certAlipayRequest.setFormat(AlipayConfig.format);
		// 设置字符集
		certAlipayRequest.setCharset(AlipayConfig.charset);
		// 设置签名类型
		certAlipayRequest.setSignType(AlipayConfig.sign_type);
		// 设置应用公钥证书路径
		// File path = new File(ResourceUtils.getURL("classpath:").getPath());
//		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("alipayRootCert.crt");
		logger.info("start----");
		URL resource1 = this.getClass().getResource("/alipayRootCert.crt");
		if(resource1 != null){
			String path1 = resource1.getPath();
			logger.info(path1);
		}
//		logger.info(inputStream.toString());
		URL resource2 = this.getClass().getResource("/alipayCertPublicKey_RSA2.crt");
		if(resource2 != null){
			String path2 = resource2.getPath();
			logger.info(path2);
		}
		URL resource3 = this.getClass().getResource("/appCertPublicKey_2019102168481752.crt");
		if(resource3 != null){
			String path3 = resource3.getPath();
			logger.info(path3);
		}
		URL resource4 = this.getClass().getResource("/appCertPublicKey_201910216848175222.crt");
//		String inputStream2Str = inputStream2Str(inputStream);
		
		if(resource4 != null){
			String path4 = resource4.getPath();
			logger.info(path4);
		}
		logger.info("end----");
		// certAlipayRequest.setCertPath(AlipayConfig.app_cert_path);
		// // 设置支付宝公钥证书路径
		// certAlipayRequest.setAlipayPublicCertPath(AlipayConfig.alipay_cert_path);
		// // 设置支付宝根证书路径
		// certAlipayRequest.setRootCertPath(AlipayConfig.alipay_root_cert_path);
		return certAlipayRequest;
	}

	/**
	 * 将inputStream转换为str
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String inputStream2Str(InputStream is) {

		StringBuffer sb = new StringBuffer();
		;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is));

			String data;
			while ((data = br.readLine()) != null) {
				sb.append(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	/**
	 * 判断对象是否含有空的属性列
	 * 
	 * @param obj
	 * @return
	 */
	private boolean checkObjFieldIsNull(Object obj) {

		boolean flag = false;
		try {
			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);// 类中的成员变量为private,故必须进行此操作
				logger.debug(f.getName());
				if (f.get(obj) == null || f.get(obj).equals("")) {
					flag = true;
					return flag;
				}
			}
		} catch (IllegalAccessException e) {
			logger.error("checkObjFieldIsNull", e);
			e.printStackTrace();
		}
		return flag;
	}
}
