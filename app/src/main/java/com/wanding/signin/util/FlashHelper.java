package com.wanding.signin.util;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

/**
 *  控件闪烁，仿控件被点击效果
 **/
public class FlashHelper {

    private FlashHelper() {}

    private static class Holder {
        private static FlashHelper instance = new FlashHelper();
    }

    public static FlashHelper getInstance() {
        return FlashHelper.Holder.instance;
    }

    /**开启View闪烁效果**/
    public void startFlick( View view ) {
        if (null == view) {
            return;
        }
        Animation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(alphaAnimation);
    }

    /**取消View闪烁效果**/
    public void stopFlick( View view ) {
        if (null == view) {
            return;
        }
        view.clearAnimation();
    }
}
