package com.wanding.signin.bean;

public class FacePayAuthInfoReq {

	/**
	 * 商户号
	 */
	private String mch_no;

	/**
	 * 终端号
	 */
	private String term_no;

	/**
	 * 随机字符串(32位字母+数字)
	 */
	private String nonce_str;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 签名类型，目前支持MD5，默认为MD5
	 */
	private String sign_type;

	/**
	 * 支付类型（ALI/WX）
	 */
	private String pay_type;

	/**
	 * 附加数据（可以为空字符串）
	 */
	private String attach;

	/**
	 * 初始化数据
	 */
	private String rawdata;

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

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getRawdata() {
		return rawdata;
	}

	public void setRawdata(String rawdata) {
		this.rawdata = rawdata;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FacePayAuthInfoReq [mch_no=");
		builder.append(mch_no);
		builder.append(", term_no=");
		builder.append(term_no);
		builder.append(", nonce_str=");
		builder.append(nonce_str);
		builder.append(", sign=");
		builder.append(sign);
		builder.append(", sign_type=");
		builder.append(sign_type);
		builder.append(", pay_type=");
		builder.append(pay_type);
		builder.append(", attach=");
		builder.append(attach);
		builder.append(", rawdata=");
		builder.append(rawdata);
		builder.append("]");
		return builder.toString();
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

}
