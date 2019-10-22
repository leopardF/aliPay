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
	public static String app_id = "2019102168481752";
	// 置应用私钥
	public static String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCJM73Yk7lwjZ5pZC+xiEDRBxOzpEUl53u0EjhO8raucKZ6ht+urjkQkyAmy1E6WWmqqarVOOui3JC4DR8LCJhMohk5cHCNvQifjTZLK9VbUeQFJVlaKxe4qg3ezxw5rr/e4gdqWC58/5iMnKSYHEqKduT25juVIBQCCpBlik7dkweH9/+FPN2e4ZoD3QODwtqPLY5B1UROpNzaIdhNq/xLBjzCSn+GYURptVGzBN/fF5by2HIAYm8aFVDVZUzCOS1QZz2Ao8fiaDBwPtcP0XsV2P/Z+OTb5Rk2VK5XPHjvczz4z6FkvLp263SsMAdmq/5GtiGg8lBFr3YhmskB3OWBAgMBAAECggEAYQpQn2s0gLAZt+8dcJKvTWiU/b0VD08fhmhQnT48CTsx9H2KndEqGVgGKIorZHqzKvsgEflVaHZagWL+bt5gYetQrfiiEw39mGPXX+mb2SAdh6o3/Xd3KqxUbR/NldrMaIczuXqvPsfFDMcJ/3AbJL4dCagjwkDTBfKU2MyvJTV41HPAZr+HJ9Rm/0HNu+TUAhbnwA5o3Pojt2cQuXFLB3uhxtA5+o6bi+2eMg1RlAzeN3judDiXQjvzNn8VrgFCb72ZCsAB2GKSSz8HJvu6+2u8U3ZwOEJtS1nM4dmVSIe0deF43VIjK7JxWm5oBdFeMmFMWtoHIPnlbBlI7vgWFQKBgQDenwIVV5PGhusg03IEKmZ+6f4zjW2n/jSRkAoQgSPSYHzVIzG/6RrtuoyZolYepOgQGe92WnZ60f8DHrdQrZIQY7ayk6kXAAiPdviT5u7VRaC98y/amTrOFvcupWrpm4hAoWzHuPsXiklmpp7U1PV60Ja/CRo80MA403pIMqq5UwKBgQCdxgwsLz2M0mkfdiUVfHufkMdQ5uHvs40rTyiV89q41PG/C6XivaQPgMFaWZkJGAqAC/wFR3QObBE27e8XqUXK6+e6Yapbp1lErVbW9jbG+iaTH+/ZzgIgJSFrjJpoeVoMy9GlMa9ltcyGbQALOpJM2l7Q/P+JadmdiPxF7z1HWwKBgQDOsXONvTJHMR097RP/Kh7f1wFdfAJD55GHVOUDTeOkgFFm0rZuKFAISRwhyMeMzC9qQ6pBiNxYTIEotGwfTAhBj1Mhmld0M2O/UAaby94TxgHoWtIjdAFVN51ogc0x+GYZG8eT5/u5b3uXAzyU/uSskt5joF/r9yWBnVf7kI9SYQKBgQCX13/lkj15ZxNW/Rxplllf0zWQDfSJ/dKySMe1sW/iDMbwZ6RqGLtpVDBB5RMcpm+MlnwhFIiETMswg0NlETsjNXDadp4/AUsvhmeaEHHdg+hWFhl/E/4Fs9Z7LGF6hLf+srX8d1nn2FSTVxoAtIkSv+Ma3TKNrDqMm8a3A62FGwKBgC4JSzPFp3W52TqRKqD1FRowmyqsuCXmiIcUIdgDsyEmfB3X9GcDCUkO66sjb9ud8FqeYiYCqpzq3d0/sbhRGst6uubNTFDA4YS6kbN8OFetqqJVMuBe7eK2K33Q9pPVC/y70PbIcjUFaYkjKMNtEK7V6nximvvKUxtt8o+TXlTP";
	// 设置请求格式，固定值json
	public static String format = "json";
	// 设置字符集
	public static String charset = "utf-8";
	// 设置签名类型
	public static String sign_type = "RSA2";

}