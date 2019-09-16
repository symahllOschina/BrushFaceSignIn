package com.wanding.signin.bean;

public class FacePayAuthInfoRes {

	/**
	 * 返回状态码
	 */
	private String return_code;

	/**
	 * 返回信息
	 */
	private String return_msg;

	/**
	 * 业务结果
	 */
	private String result_code;

	/**
	 * 错误代码
	 */
	private String err_code;

	/**
	 * 错误代码描述
	 */
	private String err_code_des;

	/**
	 * 商户公众号
	 */
	private String appid;
	/***
	 * 商户号
	 */
	private String mch_id;

	/**
	 * 子商户号(服务商模式)
	 */
	private String sub_mch_id;

	/**
	 * 子商户公众号id
	 */
	private String sub_appid;
	/**
	 * STORE_ID门店id
	 */
	private String store_id;


	/**
	 * 商户号
	 */
	private String mch_no;

	/**
	 * 终端号
	 */
	private String term_no;

	/**
	 * 随机字符串
	 */
	private String nonce_str;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 签名
	 */
	private String sign_type;

	/**
	 * SDK调用凭证。用于调用SDK的人脸识别接口。
	 */
	private String authinfo;

	/**
	 * authinfo的有效时间, 单位秒
	 */
	private String expires_in;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	public String getSub_appid() {
		return sub_appid;
	}

	public void setSub_appid(String sub_appid) {
		this.sub_appid = sub_appid;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getMch_no() {
		return mch_no;
	}

	public void setMch_no(String mch_no) {
		this.mch_no = mch_no;
	}

	public String getTerm_no() {
		return term_no;
	}

	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAuthinfo() {
		return authinfo;
	}

	public void setAuthinfo(String authinfo) {
		this.authinfo = authinfo;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FacePayAuthInfoRes [return_code=");
		builder.append(return_code);
		builder.append(", return_msg=");
		builder.append(return_msg);
		builder.append(", result_code=");
		builder.append(result_code);
		builder.append(", err_code=");
		builder.append(err_code);
		builder.append(", err_code_des=");
		builder.append(err_code_des);

		builder.append(", appid=");
		builder.append(appid);
		builder.append(", sub_appid=");
		builder.append(sub_appid);
		builder.append(", mch_id=");
		builder.append(mch_id);
		builder.append(", sub_mch_id=");
		builder.append(sub_mch_id);
		builder.append(", store_id=");
		builder.append(store_id);


		builder.append(", mch_no=");
		builder.append(mch_no);
		builder.append(", term_no=");
		builder.append(term_no);
		builder.append(", nonce_str=");
		builder.append(nonce_str);
		builder.append(", sign=");
		builder.append(sign);
		builder.append(", authinfo=");
		builder.append(authinfo);
		builder.append(", expires_in=");
		builder.append(expires_in);
		builder.append("]");
		return builder.toString();
	}




	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

}
