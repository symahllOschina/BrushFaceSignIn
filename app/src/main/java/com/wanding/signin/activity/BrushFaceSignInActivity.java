package com.wanding.signin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.wxpayface.IWxPayfaceCallback;
import com.tencent.wxpayface.WxPayFace;
import com.tencent.wxpayface.WxfacePayCommonCode;
import com.wanding.signin.BaseActivity;
import com.wanding.signin.Constants;
import com.wanding.signin.NitConfig;
import com.wanding.signin.R;
import com.wanding.signin.bean.FacePayAuthInfoRes;
import com.wanding.signin.httputil.HttpURLConnectionUtil;
import com.wanding.signin.httputil.NetworkUtils;
import com.wanding.signin.requtil.ReqParUtil;
import com.wanding.signin.util.DecimalUtil;
import com.wanding.signin.util.FastJsonUtil;
import com.wanding.signin.util.ReturnXMLParser;
import com.wanding.signin.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *  刷脸签到
 */
@ContentView(R.layout.activity_brush_face_signin)
public class BrushFaceSignInActivity extends BaseActivity {

    private String mAuthInfo = "";

    public static final int RESULT_CODE = 0x002;
    private boolean isSignSuccess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        isSignSuccess = intent.getBooleanExtra("isSignSuccess",isSignSuccess);

        getRawData();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopBrushFace();
    }

    /**
     * 获取RawData
     */
    private void getRawData(){
        try{
            WxPayFace.getInstance().getWxpayfaceRawdata(new IWxPayfaceCallback() {
                @Override
                public void response(Map info) throws RemoteException {
                    if (!isSuccessInfo(info)) {
                        return;
                    }
                    Log.e("获取rawdata返回:",info.toString());
                    String rawdata = info.get("rawdata").toString();
                    getAuthInfo(rawdata);


                    //获取人脸SDK调用凭证authinfo
                    /*try {
                        String encodeRawdata = URLEncoder.encode(rawdata,"UTF-8");
                        Map<String,String> map = ReqParUtil.getAuthinfoReq(encodeRawdata);
                        getAuthInfo(map);
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToast("获取authinfo参数配置失败！");
                    }*/
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            showToast("获取RawData参数失败！");
        }
    }

    /**
     * 准备获取人脸凭证（1，调用摄像头，展示UI，2，完成人脸识别，3，返回人脸识别结果face_code,openid）
     *  ask_face_permit: 支付成功页是否需要展示人脸识别授权项。展示：1。不展示：0。
     *                   人脸识别授权项：用户授权后用于1:N识别，可返回用户信息openid，建议商户有自己会员系统时，填1。
     *  ask_ret_page: 是否展示微信支付成功页，可选值："0"，不展示；"1"，展示
     *
     *  total_fee: 订单金额(数字), 单位分，该字段在在face_code_type为"1"时可不填，为"0"时必填
     */
    private void startBrushFace(FacePayAuthInfoRes authInfo){
        try{
//            HashMap params = new HashMap();
//            //必填参数
//            params.put(Constants.PARAMS_APPID, "wx2b029c08a6232582");
//            params.put(Constants.PARAMS_MCH_ID, "1900007081");
//            params.put(Constants.PARAMS_SUB_APPID, "");
//            params.put(Constants.PARAMS_SUB_MCH_ID, "");
//            params.put(Constants.PARAMS_STORE_ID, "12345");
//            params.put(Constants.PARAMS_FACE_AUTHTYPE, "FACEPAY");
//            params.put(Constants.PARAMS_AUTHINFO, "CLsyXTeRNXjvc/ohcmEesRfJHlMP+gXVaSQUyaYh4i6mMjQuq+qnln7+feruyHTsemP5qLSQZ0xrxYul4xifjHcvBkobCSlzLYSQUDFzbamO2uVVphPh2vACgxTdF6XxJU2KbsnN5NzuQXoXx8Qb08qpziZ/u+uidYQhfkkFzwfFgtHA7HMX32cLwORSWfEUi9r9ljbE3k+QI+qEVdb5m/OHEf39IkD06aQMdQLbstKnVK4qLuOLByKyLYNcp+6ZFd5WNwA/MJxOOAJb9Qg7bAtW70OGz0lGvCmG2MGY91OopNWv0AZCvlxxpQh/KRcFwLl6C3w2TywZh5YjLld9B+VT1iM11dGpZ1NtWkPewkUWAqc5hz12zdiE1WIzSkxXSnEbX1Sx1OtynT1ytpYVQQN3AWKBW2I4jN/8HoF2jAxSxXusZoBMk1mT8+n1TrwzZM85IZ4nU6ir0Gk2JPA/nEvbQ5zeoXz7tiR/i2GD2Tr3IpW/2DUAvoQGkuXk9BzUZJFYmkjFs1dNXHb0MnTsQvI5otd3m//ZHrvUw8FJXCJGh2bLgNc3afXCip5VZE1pGkPImu5lhj7Phgf4RAFfmY2eAxClWU+gGggSVLwERV6ESjpWXzhZzQEcyyBVsk8wgERJfOEQ0qpB7w==");
//            params.put(Constants.PARAMS_ASK_FACE_PERMIT, "1");
//            //选填参数
//            String phone = "";
//            params.put(Constants.PARAMS_TELEPHONE, phone);
//            params.put(Constants.PARAMS_OUT_TRADE_NO, "" + (System.currentTimeMillis() / 100000));
//            params.put(Constants.PARAMS_TOTAL_FEE, "22222");
//            params.put(Constants.PARAMS_FACE_CODE_TYPE, "0");
//            params.put(Constants.PARAMS_ASK_RET_PAGE, "1");
            //支付时金额以分为单位

            final Map params = ReqParUtil.getWxpayfaceUserInfoParams(authInfo);
            Log.e("刷脸SDK参数：",params.toString());

            WxPayFace.getInstance().getWxpayfaceUserInfo(params, new IWxPayfaceCallback() {
                @Override
                public void response(Map info) throws RemoteException {

                    Log.e("获取人脸凭证返回:",info.toString());
                    if (!isSuccessInfo(info)) {
                        finish();
                        return;
                    }
                    String face_code = info.get("face_code").toString();
                    String openid = info.get("openid").toString();
                    ToastUtil.showText(activity,"签到成功！",1);
                    finish();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            showToast("人脸识别SDK调起失败！");
        }
    }


    private void doFaceRecognize(boolean once) {
        HashMap params2 = new HashMap();
        if (once) {
            params2.put(Constants.PARAMS_FACE_AUTHTYPE, "FACEID-ONCE");
        } else {
            params2.put(Constants.PARAMS_FACE_AUTHTYPE, "FACEPAY");
        }
        params2.put(Constants.PARAMS_APPID, "wx2b029c08a6232582");
        params2.put(Constants.PARAMS_MCH_ID, "1900007081");
        params2.put(Constants.PARAMS_MCH_NAME, "科脉自助收银");
//                params2.put(PARAMS_MCH_ID,"12306");
//                params2.put(PARAMS_STORE_ID,"12345");
//                params2.put(PARAMS_SUB_APPID,"33333");
//                params2.put(PARAMS_SUB_MCH_ID,"44444");
        params2.put(Constants.PARAMS_OUT_TRADE_NO, "" + (System.currentTimeMillis() / 100000));
        params2.put(Constants.PARAMS_TOTAL_FEE, "22222");
        String phone2 = "";
        params2.put(Constants.PARAMS_TELEPHONE, phone2);
        params2.put(Constants.PARAMS_AUTHINFO, mAuthInfo);
        WxPayFace.getInstance().getWxpayfaceUserInfo(params2, new IWxPayfaceCallback() {
            @Override
            public void response(final Map info) throws RemoteException {

                Log.e(TAG, "response | getWxpayfaceUserInfo " + info.toString());
                final String code = (String) info.get("return_code"); // 错误码
                String msg = (String) info.get("return_msg"); // 错误码描述

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(code.equals(Constants.RETURN_SUCCESS)){
                                isSignSuccess = true;
                            }
                            Intent intent = new Intent();
                            intent.putExtra("isSignSuccess",isSignSuccess);
                            setResult(RESULT_CODE,intent);
                            finish();


                        }
                    });




            }
        });
    }
    /**
     *  获取人脸SDK调用凭证authinfo
     */
    private void getAuthInfo(final Map<String,String> map){
        showWaitDialog("正在加载");
        final String url = NitConfig.getAuthInfoUrl;
        new Thread(){
            @Override
            public void run() {
                try {
                    JSONObject userJSON = new JSONObject();
                    userJSON.put("face_code","AAAAAA");
                    userJSON.put("orderinfo","BBBBBBBBB");
                    String reqJson = String.valueOf(userJSON);
                    Log.e("获取authinfo示例请求参数：", reqJson);

                    String content = FastJsonUtil.toJSONString(map);
                    String content_type = HttpURLConnectionUtil.CONTENT_TYPE_JSON;
                    Log.e("获取authinfo真实请求参数：", content);
                    String jsonStr = HttpURLConnectionUtil.doPos(url,content,content_type);
                    Log.e("获取authinfo返回结果：", jsonStr);
                    int msg = NetworkUtils.MSG_WHAT_ONE;
                    String text = jsonStr;
                    sendMessage(msg,text);
                } catch (JSONException e) {
                    e.printStackTrace();
                    sendMessage(NetworkUtils.REQUEST_JSON_CODE,NetworkUtils.REQUEST_JSON_TEXT);
                }catch (IOException e){
                    e.printStackTrace();
                    sendMessage(NetworkUtils.REQUEST_IO_CODE,NetworkUtils.REQUEST_IO_TEXT);
                } catch (Exception e) {
                    e.printStackTrace();
                    sendMessage(NetworkUtils.REQUEST_CODE,NetworkUtils.REQUEST_TEXT);
                }
            }
        }.start();

    }

    private void getAuthInfo(String rawdata){
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient client = new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .build();


            okhttp3.RequestBody body = okhttp3.RequestBody.create(null, rawdata);

            Request request = new Request.Builder()
                    .url("https://wxpay.wxutil.com/wxfacepay/api/getWxpayFaceAuthInfo.php")
                    .post(body)
                    .build();

            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(okhttp3.Call call, IOException e) {
                            Log.d(TAG, "onFailure | getAuthInfo " + e.toString());
                        }

                        @Override
                        public void onResponse(okhttp3.Call call, Response response) throws IOException {
                            try {
                                mAuthInfo = ReturnXMLParser.parseGetAuthInfoXML(response.body().byteStream());
                                doFaceRecognize(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(int what,String text){
        Message msg = new Message();
        msg.what = what;
        msg.obj = text;
        handler.sendMessage(msg);
    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String errorJsonText = "";
            switch (msg.what){
                case NetworkUtils.MSG_WHAT_ONE:
                    String authInfoJson = (String) msg.obj;
                    authInfoJson(authInfoJson);
                    hideWaitDialog();
                    break;
                case NetworkUtils.REQUEST_JSON_CODE:
                    errorJsonText = (String) msg.obj;
                    ToastUtil.showText(activity,errorJsonText,1);
                    hideWaitDialog();
                    break;
                case NetworkUtils.REQUEST_IO_CODE:
                    errorJsonText = (String) msg.obj;
                    ToastUtil.showText(activity,errorJsonText,1);
                    hideWaitDialog();
                    break;
                case NetworkUtils.REQUEST_CODE:
                    errorJsonText = (String) msg.obj;
                    ToastUtil.showText(activity,errorJsonText,1);
                    hideWaitDialog();
                    break;
                default:
                    finish();
                    break;
            }
        }
    };

    private void authInfoJson(String json){
        FacePayAuthInfoRes authInfoRes = null;
        try {
            authInfoRes = (FacePayAuthInfoRes) FastJsonUtil.jsonToObject(json,FacePayAuthInfoRes.class);
            if(authInfoRes != null){
                String return_code = authInfoRes.getReturn_code();
                String return_msg = authInfoRes.getReturn_msg();
                String result_code = authInfoRes.getResult_code();
                String err_code_des = authInfoRes.getErr_code_des();
                if(Constants.RETURN_SUCCESS.equals(return_code)){
                    if(Constants.RETURN_SUCCESS.equals(result_code)){
                        //准备获取人脸凭证（1，调用摄像头，展示UI，2，完成人脸识别，3，返回人脸识别结果face_code,openid）
                        startBrushFace(authInfoRes);
                    }else{
                        ToastUtil.showText(activity,err_code_des,1);
                    }
                }else{
                    ToastUtil.showText(activity,return_msg,1);
                }
            }else{
                ToastUtil.showText(activity,"获取authinfo失败！",1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showText(activity,"获取authinfo失败！",1);
        }
    }

    /**
     * 停止人脸识别
     */
    private void stopBrushFace(){
        try{
            HashMap map = new HashMap();
            WxPayFace.getInstance().stopWxpayface(map, new IWxPayfaceCallback() {
                @Override
                public void response(Map info) throws RemoteException {
                    if (!isSuccessInfo(info)) {
                        return;
                    }
                    showToast("停止人脸识别！");
                }
            });
        }catch(Exception e){
            e.printStackTrace();
            showToast("停止人脸识别调起失败！！");
        }
    }

    private boolean isSuccessInfo(Map mapInfo) {
        if (mapInfo == null) {
            showToast("调用返回为空, 请查看日志");
            new RuntimeException("调用返回为空").printStackTrace();
            return false;
        }
        String code = (String)mapInfo.get(Constants.RETURN_CODE);
        String msg = (String)mapInfo.get(Constants.RETURN_MSG);
        Log.e(TAG, "response | getWxpayfaceRawdata " + code + " | " + msg);
        if (code == null || !code.equals(WxfacePayCommonCode.VAL_RSP_PARAMS_SUCCESS)) {
            showToast("调用返回非成功信息, 请查看日志");
            new RuntimeException("调用返回非成功信息: " + msg).printStackTrace();
            finish();
            return false;
        }
        Log.e(TAG, "调用返回成功");
        return true;
    }




}
