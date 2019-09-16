package com.wanding.signin;


/**
 * 服务地址管理类
 */
public class NitConfig {
	
	/**  打包前必看：
	 */
	/** 测试服务前缀 */						  // test.weupay.com/pay/api/qmp/100/1/barcodepay
	//支付链接前缀									 
	public static final String basePath =  "http://dev.weupay.com/pay/api/qmp/100";
	//查询业务链接前缀
	public static final String queryBasePath = "http://dev.weupay.com/pay/api/qmp/200";//test:dev
	//交易明细查询（历史）
	public static final String querySumHistoryPath = "http://test.weupay.com:8081/download/api/qmp/200";//test:dev
	//核销业务
	public static final String writeOffBasePath = "http://dev.weupay.com/admin/api/qmp/200";//


	public static final String newBasePath = "https://dev.wandingkeji.cn";


	/** 正式服务器 */
	//支付链接前缀
	public static final String basePath1 =      "http://weixin.weupay.com/pay/api/qmp/100";
	//查询业务链接前缀
	public static final String queryBasePath1 = "http://weixin.weupay.com/pay/api/qmp/200";
	//交易明细查询（历史）
	public static final String querySumHistoryPath1 = "http://download.weupay.com/download/api/qmp/200";//test:dev
	//核销业务，批量制劵
	public static final String writeOffBasePath1 = "http://weixin.weupay.com/admin/api/qmp/200";

	
	
	/**
	 * 正式服务器图片前缀 
	 */
	public static final String imgUrl="";
	
	/**
	 * 本地服务器图片前缀 
	 */
	public static final String imgUrls="";


	/**
	 *  获取人脸SDK调用凭证authinfo
		 *  参数：人脸SDK返回的rawdata
	 */
//	public static final String getAuthInfoUrl = queryBasePath +   "/1/"queryOrderSum;
	public static final String getAuthInfoUrl = "https://dev.weupay.com/pay/api/face/authinfo";


	/**
	 * 人脸支付订单支付
	 */
	public static final String brushFaceOrderPayUrl = "https://dev.weupay.com/pay/api/face/placeorder";


	
	/**
	 * 佳博云打印---》》查询打印机状态
	 */
	public static final String queryDeviceListUrl = "http://api.poscom.cn:80/apisc/getStatus";
	/**
	 * 佳博云打印---》》发送数据到云打印设备执行打印
	 */
	public static final String wifiSendMsgUrl = "http://api.poscom.cn:80/apisc/sendMsg";

	
	
	
	
	
	
	
	
}
