package com.wanding.signin.util;

import android.util.Log;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;


public class ParSignUtils {

	public static String generateSignature(final Map<String, String> data, String key, String signType)
			throws Exception {
		Set<String> keySet = data.keySet();
		String[] keyArray = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keyArray);
		StringBuilder sb = new StringBuilder();
		for (String k : keyArray) {
			if ("sign".equals(k)) {
				continue;
			}
			if (data.get(k).trim().length() > 0){
				// 参数值为空，则不参与签名
				sb.append(k).append("=").append(data.get(k).trim()).append("&");
			}
		}
		sb.append("key=").append(key);
		Log.e("sign",sb.toString());
		if ("MD5".equals(signType)) {
			return MD5.MD5Encode(sb.toString()).toUpperCase();
		} else {
			throw new Exception(String.format("Invalid sign_type: %s", signType));
		}
	}

	public static boolean isSignatureValid(Map<String, String> data, String key, String signType) throws Exception {
		if (!data.containsKey("sign")) {
			return false;
		}
		String sign = data.get("sign");
		return generateSignature(data, key, signType).equals(sign);
	}
}
