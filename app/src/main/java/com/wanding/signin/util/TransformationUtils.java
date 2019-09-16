package com.wanding.signin.util;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;

/**
 *  使用Glide加载图片等比例缩放
 */
public class TransformationUtils extends ImageViewTarget<Bitmap> {

    private ImageView img;

    public TransformationUtils(ImageView img) {
        super(img);
        this.img = img;
    }

    @Override
    protected void setResource(@Nullable Bitmap resource) {

        view.setImageBitmap(resource);

        //获取原图的宽高
        int width = resource.getWidth();
        int height = resource.getHeight();

        //获取imageView的宽
        int imageViewWidth = img.getWidth();

        //计算缩放比例
        float sy = (float) (imageViewWidth * 0.1) / (float) (width * 0.1);

        //计算图片等比例放大后的高
        int imageViewHeight = (int) (height * sy);
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.height = imageViewHeight;
        img.setLayoutParams(params);

    }
}
