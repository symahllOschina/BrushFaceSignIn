package com.wanding.signin.util;



import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wanding.signin.R;


public class ToastUtil extends Toast{
	/**
     * Toast单例
     */
    private static ToastUtil toast;
    
    /**
     * 吐司上的图片
     */
    private static ImageView toast_img;

    /**
     * 构造
     *
     * @param context
     */
    public ToastUtil(Context context) {
        super(context);
    }
    
    
    /**
     * 隐藏当前Toast
     */
    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    @Override
    public void cancel() {
        try {
            super.cancel();
        } catch (Exception e) {

        }
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {

        }
    }
    
    /**
     * 初始化Toast
     *
     * @param context 上下文
     * @param text    显示的文本
     *        gravity 1:底部，2：居中
     */
    private static void initToast(Context context, CharSequence text,int gravity) {
        try {
            cancelToast();

            toast = new ToastUtil(context);

            // 获取LayoutInflater对象
            LayoutInflater inflater = 
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 由layout文件创建一个View对象
            View layout = inflater.inflate(R.layout.add_shopcard_success_toast, null);

            // 吐司上的图片
            toast_img = (ImageView) layout.findViewById(R.id.msg_img);

            // 吐司上的文字
            TextView toast_text = (TextView) layout.findViewById(R.id.msg_text);
            toast_text.setText(text);
            toast.setView(layout);
            if(gravity == 1){
                toast.setGravity(Gravity.BOTTOM, 0, 70);
            }else if(gravity == 2){
                toast.setGravity(Gravity.CENTER, 0, 70);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 图标状态 不显示图标
     */
    private static final int TYPE_HIDE = -1;
    /**
     * 图标状态 显示√
     */
    private static final int TYPE_TRUE = 0;
    /**
     * 图标状态 显示×
     */
    private static final int TYPE_FALSE = 1;

    /**
     * 显示Toast
     *
     * @param context 上下文
     * @param text    显示的文本
     * @param time    显示时长
     * @param imgType 图标状态
     *        gravity 显示位置
     *
     */
    private static void showToast(Context context, CharSequence text, int time, int imgType,int gravity) {
        // 初始化一个新的Toast对象
        initToast(context, text,gravity);

//        // 设置显示时长
//        if (time == Toast.LENGTH_LONG) {
//            toast.setDuration(Toast.LENGTH_LONG);
//        } else {
//            toast.setDuration(Toast.LENGTH_SHORT);
//        }

        // 判断图标是否该显示，显示√还是×
        if (imgType == TYPE_HIDE) {
            toast_img.setVisibility(View.GONE);
        } else {
            if (imgType == TYPE_TRUE) {
                toast_img.setBackgroundResource(R.drawable.addshopcard_success);
            } else {
                toast_img.setBackgroundResource(R.drawable.addshopcard_success);
            }
            toast_img.setVisibility(View.VISIBLE);

            // 动画
            ObjectAnimator.ofFloat(toast_img, "rotationY", 0, 360).setDuration(1700).start();
        }

        // 显示Toast
        toast.show();
    }
    
    
    /**
     * 显示一个纯文本吐司
     *
     * @param context 上下文
     * @param text    显示的文本
     * @param gravity 显示位置
     */
    public static void showText(Context context, CharSequence text,int gravity) {
        showToast(context, text, Toast.LENGTH_SHORT, TYPE_HIDE,gravity);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context   上下文
     * @param text      显示的文本
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static void showText(Context context, CharSequence text, boolean isSucceed,int gravity) {
        showToast(context, text, Toast.LENGTH_SHORT, isSucceed ? TYPE_TRUE : TYPE_FALSE,gravity);
    }

    /**
     * 显示一个纯文本吐司
     *
     * @param context 上下文
     * @param text    显示的文本
     * @param time    持续的时间
     */
    public static void showText(Context context, CharSequence text, int time,int gravity) {
        showToast(context, text, time, TYPE_HIDE,gravity);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context   上下文
     * @param text      显示的文本
     * @param time      持续的时间
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static void showText(Context context, CharSequence text, int time, boolean isSucceed,int gravity) {
        showToast(context, text, time, isSucceed ? TYPE_TRUE : TYPE_FALSE,gravity);
    }
}
