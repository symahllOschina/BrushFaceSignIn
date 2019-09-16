package com.wanding.signin.util;
/**
 * 倒计时关闭Dialog
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanding.signin.R;

/**
 * Author:Created by Thorn on 2019/1/9
 * Function: alert a CountDownTimer Dialog
 */
@SuppressWarnings("WeakerAccess")
public class TimerDialogUtil {


    /**
     * 三种图标状态
     */
    public static final int ICON_SUCCESS = 0;
    public static final int ICON_FAIL = 1;
    public static final int ICON_WARN = 2;

    /**
     * @param context              context
     * @param message              文字描述
     * @param iconType             图标类型
     * @param timerCount           毫秒为单位
     * @param onTimerClickListener listener
     */
    public static void start(final Activity context, final String message, final int iconType, final int timerCount, final OnTimerClickListener onTimerClickListener) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.activity_signin_hint, null);
        builder.setView(view);
        ImageView iv_icon = view.findViewById(R.id.signin_hint_img);
        final TextView tv_message = view.findViewById(R.id.signin_hint_tvMsg);
        final TextView tv_confirm = view.findViewById(R.id.signin_hint_tvOk);
        switch (iconType) {
            case ICON_SUCCESS:
                iv_icon.setImageResource(R.drawable.signin_success_icon);
                break;
            case ICON_FAIL:
                iv_icon.setImageResource(R.drawable.signin_fail_icon);
                break;
            case ICON_WARN:
                iv_icon.setImageResource(R.drawable.signin_unknown_icon);
                break;
            default:
                iv_icon.setImageResource(R.drawable.signin_unknown_icon);
                break;
        }

        tv_message.setText(message);
        builder.setCancelable(false);
        final android.support.v7.app.AlertDialog dialog = builder.show();

        final CountDownTimer countDownTimer = new CountDownTimer(timerCount, 1000) {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onTick(long millisUntilFinished) {
                int remainTime = (int) (millisUntilFinished / 1000L);
                tv_message.setText(message);
                tv_confirm.setText("确定" + "(" + remainTime + "s)");
            }

            @Override
            public void onFinish() {
                if (dialog != null) {
                    dialog.cancel();
                }
                if (onTimerClickListener != null) {
                    onTimerClickListener.finish();
                }
            }
        };
        countDownTimer.start();

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null) {
                    dialog.cancel();
                }
                //noinspection ConstantConditions
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                if (onTimerClickListener != null) {
                    onTimerClickListener.finish();
                }
            }
        });
    }


    /**
     * 默认倒计时，时间长度为 5 秒
     *
     * @param context              context
     * @param message              消息
     * @param iconType             图标类型
     * @param onTimerClickListener listener
     */
    public static void start(final Activity context, final String message, final int iconType, final OnTimerClickListener onTimerClickListener) {
        int timerCount = 5000;
        start(context, message, iconType, timerCount, onTimerClickListener);
    }

    /**
     * 点击接口类
     */
    public interface OnTimerClickListener {
        void finish();
    }
}