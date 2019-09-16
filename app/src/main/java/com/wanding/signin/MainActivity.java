package com.wanding.signin;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.tencent.wxpayface.IWxPayfaceCallback;
import com.tencent.wxpayface.WxPayFace;
import com.tencent.wxpayface.WxfacePayCommonCode;
import com.wanding.signin.activity.BrushFaceSignInActivity;
import com.wanding.signin.util.TimerDialogUtil;
import com.wanding.signin.util.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements OnBannerListener,View.OnClickListener {

    @ViewInject(R.id.main_banner)
    private Banner banner;

    @ViewInject(R.id.main_layoutMoney)
    LinearLayout layoutMoney;

    private static final int REQUEST_PERMISSION = 0x001;//动态权限注册请求码

    private static final int REQUEST_CODE = 0x002;

    /**
     * banner图集合
     */
    private ArrayList<Integer> list_path;
    private ArrayList<String> list_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerPermission();
        initView();
        initListener();

        //初始化人脸SDK
        initWxpayface();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            WxPayFace.getInstance().releaseWxpayface(activity);
        }catch (Exception e){
            e.printStackTrace();
            showToast("人脸SDK不存在！");
        }
    }


    /** 初始化View  */
    private void initView(){

        //放图片地址的集合
        list_path = new ArrayList<>();
        list_path.add(R.drawable.banner01_icon);
        list_path.add(R.drawable.banner02_icon);
        list_path.add(R.drawable.banner03_icon);

//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        //放标题的集合
        list_title = new ArrayList<>();
        list_title.add("1");
        list_title.add("2");
        list_title.add("3");
//        list_title.add("3");
//        list_title.add("4");
        //设置内置样式(显示圆形指示器和标题（水平显示)，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();

    }

    private void initListener(){
        layoutMoney.setOnClickListener(this);
    }

    /**
     * Android6.0动态注册权限
     */
    private void registerPermission(){
        /**
         *默认安装禁止SD卡的读写权限，以下方式打开权限
         */
        try {
            PackageManager pkgManager = getPackageManager();

            // 读写 sd card 权限非常重要, android6.0默认禁止的, 建议初始化之前就弹窗让用户赋予该权限
            boolean sdCardWritePermission =
                    pkgManager.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, getPackageName()) == PackageManager.PERMISSION_GRANTED;

            // read phone state用于获取 imei 设备信息
            boolean phoneSatePermission =
                    pkgManager.checkPermission(Manifest.permission.READ_PHONE_STATE, getPackageName()) == PackageManager.PERMISSION_GRANTED;

            //相机权限
            boolean cameraPermission = pkgManager.checkPermission(Manifest.permission.CAMERA,getPackageName()) == PackageManager.PERMISSION_GRANTED;
            //sd卡读取权限
            boolean sdCardReadPermission = pkgManager.checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE,getPackageName()) == PackageManager.PERMISSION_GRANTED;

            if (Build.VERSION.SDK_INT >= 23 && !sdCardWritePermission || !phoneSatePermission || !cameraPermission || !sdCardReadPermission) {
                requestPermission();
            }else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void requestPermission() {
        try {
            ActivityCompat.requestPermissions(this, new String[]
                            {
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_PHONE_STATE,
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.READ_EXTERNAL_STORAGE
                            },
                    REQUEST_PERMISSION);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnBannerClick(int position) {
        Log.e("tag", "你点了第"+position+"张轮播图");
    }

    /**
     * 初始化微信人脸SDK
     */
    private void initWxpayface(){
        try{
            Map<String,String> map = new HashMap<>();
//        map.put("ip", "192.168.1.1"); //若没有代理,则不需要此行
//        map.put("port", "8888");//若没有代理,则不需要此行
            WxPayFace.getInstance().initWxpayface(this, map, new IWxPayfaceCallback() {
                @Override
                public void response(Map map) throws RemoteException {
                    if (!isSuccessInfo(map)) {
                        return;
                    }
//                    showToast("初始化完成");

                    //获取RawData

                }
            });
        }catch (Exception e){
            e.printStackTrace();
            showToast("人脸SDK不存在！");
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



    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.main_layoutMoney:
                intent.setClass(activity,BrushFaceSignInActivity.class);
                boolean isSignSuccess = false;
                intent.putExtra("isSignSuccess",isSignSuccess);
                startActivityForResult(intent,REQUEST_CODE);

                break;
            default:
                break;
        }
    }

    /**
     *  自定义的图片加载器
     */
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(activity)
//                    .setDefaultRequestOptions( new RequestOptions()
//                            .frame(3000000)
//                            .centerCrop()
//                            .error(R.drawable.banner01_icon)
//                            .placeholder(R.drawable.banner01_icon)
//                            .fitCenter() ) //让图片填充全部
//                    .load(path)
//                    .into(imageView);


                    .load(path)
                    .format(DecodeFormat.PREFER_ARGB_8888)//设置图片解码格式
                    .placeholder(R.drawable.banner01_icon)
                    .dontAnimate()
                    .error(R.drawable.banner01_icon)
                    .into(imageView);

        }
    }

    private void signInResultShowDialog(boolean isSignSuccess){
        String message = "未知错误";
        int img_type = TimerDialogUtil.ICON_WARN;
        if(isSignSuccess){
            message = "签到成功";
            img_type = TimerDialogUtil.ICON_SUCCESS;
        }else{
            message = "签到失败";
            img_type = TimerDialogUtil.ICON_FAIL;
        }
        TimerDialogUtil.start(activity, message, img_type, 6000, new TimerDialogUtil.OnTimerClickListener() {
            @Override
            public void finish() {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean isSignSuccess = false;
        if(requestCode == REQUEST_CODE){
            if(resultCode == BrushFaceSignInActivity.RESULT_CODE){
                if(data!=null){
                    Bundle bundle = data.getExtras();
                    isSignSuccess = bundle.getBoolean("isSignSuccess");
                }
                signInResultShowDialog(isSignSuccess);
            }
        }
    }
}
