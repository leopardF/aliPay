package com.alipay;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.config.AlipayConfig;
import com.alipay.enums.AliAppPayResponseCode;
import com.alipay.enums.GoodsTypeEnum;

public class AliAppPayRequest {

	private static final Logger logger = LoggerFactory.getLogger(AliAppPayRequest.class);
	
	// Linux文件绝对路径 --- ${jar包所在路径}/crt/
//	private static final String outPath = System.getProperty("user.dir") + File.separator + "crt" + File.separator;
	// window文件相对路径
	private static final String outPath = AliAppPayRequest.class.getClass().getResource("/").getPath();
	

	/**
	 * 获取支付订单信息
	 * 
	 * @param timeoutExpress
	 *            超时时间，1表示1分钟
	 * @param subject
	 *            商品标题（最大长度256）
	 * @param body
	 *            内容（最大长度512）
	 * @param totalAmount
	 *            总金额
	 * @param outTradeNo
	 *            商户网站唯一订单号（最大长度64）
	 * @param passbackParams
	 *            回传参数（最大长度512）
	 * @param notifyUrl
	 *            异步通知回调地址
	 * @return
	 */
	public AliAppPayResponseCode appPayRequest(int timeoutExpress, String subject, String body, String totalAmount,
			String outTradeNo, String passbackParams, String notifyUrl) {

		CertAlipayRequest certAlipayRequest = getCertAlipayRequest();
		if (certAlipayRequest == null) {
			return AliAppPayResponseCode.CRT_FILE_ERROR;
		}

		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		model.setTimeoutExpress(timeoutExpress + "m");
		model.setSubject(subject);
		model.setBody(body);
		model.setTotalAmount(totalAmount);
		model.setOutTradeNo(outTradeNo);
		model.setProductCode("QUICK_MSECURITY_PAY");
		model.setGoodsType(GoodsTypeEnum.VIRTUAL.value());
		model.setPassbackParams(URLEncoder.encode(passbackParams));
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.setApiVersion("2.0");

		// 构造client
		AlipayClient alipayClient = null;
		try {
			alipayClient = new DefaultAlipayClient(certAlipayRequest);
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
		// getPath: ${jar包所在“根”路径}/crt/appCertPublicKey_2019102168481752.crt,其他两个相同
		// getPath: G:\git\leopard-console\target\classes\appCertPublicKey_2019102168481752.crt
		File appCertfile = new File(outPath + "appCertPublicKey_2019102168481752.crt");
		if (appCertfile == null || appCertfile.getPath() == null) {
			logger.error("------/crt/------找不到应用公钥证书");
			return null;
		}
		certAlipayRequest.setCertPath(appCertfile.getPath());
		// 设置支付宝公钥证书路径
		File alipayCertfile = new File(outPath + "alipayCertPublicKey_RSA2.crt");
		System.out.println(alipayCertfile.getPath());
		if (alipayCertfile == null || alipayCertfile.getPath() == null) {
			logger.error("------/crt/------找不到支付宝公钥证书");
			return null;
		}
		certAlipayRequest.setAlipayPublicCertPath(alipayCertfile.getPath());
		// 设置支付宝根证书路径
		File alipayRootCertfile = new File(outPath + "alipayRootCert.crt");
		System.out.println(alipayRootCertfile.getPath());
		if (alipayRootCertfile == null || alipayRootCertfile.getPath() == null) {
			logger.error("------/crt/------找不到支付宝根证书路径");
			return null;
		}
		certAlipayRequest.setRootCertPath(alipayRootCertfile.getPath());

		return certAlipayRequest;
	}

	/**
	 * 验证订单签名
	 * @param request
	 */
	public AliAppPayResponseCode verifyOrderInfoSign(HttpServletRequest request) {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		// 设置支付宝公钥证书路径
		File alipayCertfile = new File(outPath + "alipayCertPublicKey_RSA2.crt");
		System.out.println(alipayCertfile.getPath());
		if (alipayCertfile == null || alipayCertfile.getPath() == null) {
			logger.error("------/crt/------找不到支付宝公钥证书");
			return AliAppPayResponseCode.CRT_FILE_ERROR;
		}

		// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		// boolean AlipaySignature.rsaCertCheckV1(Map<String, String> params,
		// String publicKeyCertPath, String charset,String signType)
		boolean flag;
		try {
			flag = AlipaySignature.rsaCertCheckV1(params, alipayCertfile.getPath(), AlipayConfig.charset,
					AlipayConfig.sign_type);
			if(flag){
				AliAppPayResponseCode response = AliAppPayResponseCode.SUCCESS;
				response.setAlias(JSONObject.toJSONString(params));
				return response;
			}else{
				return AliAppPayResponseCode.VERIFY_SIGN_ERROR;
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return AliAppPayResponseCode.VERIFY_SIGN_ERROR;
		}
		
	}

}
