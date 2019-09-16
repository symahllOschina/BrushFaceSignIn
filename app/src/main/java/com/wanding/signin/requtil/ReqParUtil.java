package com.wanding.signin.requtil;

import android.util.Log;


import com.wanding.signin.Constants;
import com.wanding.signin.bean.FacePayAuthInfoReq;
import com.wanding.signin.bean.FacePayAuthInfoRes;
import com.wanding.signin.util.ParSignUtils;
import com.wanding.signin.util.FastJsonUtil;
import com.wanding.signin.util.RandomStringGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付参数封装
 */
public class ReqParUtil {

    private static final String TAG = "PayParamsReqUtil";

    private static final String APP_ID = "wx2cc486f43753d093";
    private static final String SUB_APP_ID = "";//wx4db1939cf243c83c
    private static final String MCH_ID = "1484397842";
    private static final String SUB_MCH_ID = "1490359152";
    private static final String STORE_ID = "10000001";
    private static final String MCH_NO = "1001271";//1000853
    private static final String TERM_NO = "12947";//11469
    private static final String  SIGN_TYPE = "MD5";

    private static final String SIGN_KEY = "u87x010i8idzgle0l6go1pyq14mqywga";//utaf8jd6gsflvdehb5f5ufcfx8mxgy42


    /**
     * 获取authinfo请求实体
     */
    public static Map<String,String> getAuthinfoReq(String rawdata) throws Exception{

        FacePayAuthInfoReq authInfoReq = new FacePayAuthInfoReq();
        authInfoReq.setMch_no(MCH_NO);
        authInfoReq.setTerm_no(TERM_NO);
        //随机字符串
        String nonceStr = RandomStringGenerator.getSerialNumTwo(15);
        Log.e("随机字符串",nonceStr);
        authInfoReq.setNonce_str(nonceStr);
        authInfoReq.setSign_type(SIGN_TYPE);
        authInfoReq.setPay_type(Constants.PAY_TYPE_010WX);
        authInfoReq.setAttach("AAAABBBBCCCC");
        authInfoReq.setRawdata(rawdata);
        //参数加签
        Map<String, String> map = FastJsonUtil.object2Map(authInfoReq);
        String sign = ParSignUtils.generateSignature(map,SIGN_KEY,SIGN_TYPE);
        authInfoReq.setSign(sign);

        return FastJsonUtil.object2Map(authInfoReq);



    }

    /**
     * 微信刷脸获取信息
     */
    public static Map<String,String> getWxpayfaceUserInfoParams(FacePayAuthInfoRes authinfo)throws Exception{
        Map<String,String> params = new HashMap();
        if(authinfo != null){

            //必填参数
            params.put(Constants.PARAMS_APPID, authinfo.getAppid());
            params.put(Constants.PARAMS_MCH_ID, authinfo.getMch_id());
            params.put(Constants.PARAMS_SUB_APPID, authinfo.getSub_appid());
            params.put(Constants.PARAMS_SUB_MCH_ID, authinfo.getSub_mch_id());
            params.put(Constants.PARAMS_STORE_ID, authinfo.getStore_id());
            params.put(Constants.PARAMS_FACE_AUTHTYPE, "FACEID-ONCE");
            params.put(Constants.PARAMS_AUTHINFO, authinfo.getAuthinfo());
            params.put(Constants.PARAMS_AUTH_MODE, "0");

        }
        return params;
    }

    /**
     * 微信刷脸SDK参数
     */
    public static Map<String,String> getBrushFaceParams(String totalFee,String payMode,FacePayAuthInfoRes authinfo) throws Exception{
        Map<String,String> params = new HashMap();
        if(authinfo != null){

            //必填参数
            params.put(Constants.PARAMS_APPID, authinfo.getAppid());
            params.put(Constants.PARAMS_MCH_ID, authinfo.getMch_id());
            params.put(Constants.PARAMS_SUB_APPID, authinfo.getSub_appid());
            params.put(Constants.PARAMS_SUB_MCH_ID, authinfo.getSub_mch_id());
            params.put(Constants.PARAMS_STORE_ID, authinfo.getStore_id());
            params.put(Constants.PARAMS_FACE_AUTHTYPE, "FACEPAY");
            params.put(Constants.PARAMS_AUTHINFO, authinfo.getAuthinfo());
            params.put(Constants.PARAMS_ASK_FACE_PERMIT, "1");
            params.put(Constants.PARAMS_MCH_NO, MCH_NO);
            params.put(Constants.PARAMS_TERM_NO, TERM_NO);
            //选填参数
            String phone = "";
            params.put(Constants.PARAMS_TELEPHONE, phone);
            //商户订单号
            String out_trade_no = RandomStringGenerator.getRandomNum();
            Log.e("商户订单号：",out_trade_no);
            params.put(Constants.PARAMS_OUT_TRADE_NO, out_trade_no);
            params.put(Constants.PARAMS_TOTAL_FEE, totalFee);
            params.put(Constants.PARAMS_FACE_CODE_TYPE, payMode);
            params.put(Constants.PARAMS_ASK_RET_PAGE, "1");
        }
        return params;
    }




}
