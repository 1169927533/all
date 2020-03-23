package com.example.a11699.all.buguizeliudongbolang;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.a11699.all.R;

/**
 * Create time 2020/2/27
 * Create Yu
 */
public class BolangView extends View {
    public BolangView(Context context) {
        this(context,null);
    }

    public BolangView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    Paint mPaint;
    Bitmap BmpDST, BmpSRC,BmpSRCOLD;
    int mItemWaveLength,dx = 0;

    private void init() {
        mPaint = new Paint();

        BmpDST = BitmapFactory.decodeResource(getResources(), R.drawable.wave_bg, null);

        BmpSRCOLD = BitmapFactory.decodeResource(getResources(), R.drawable.circle_shape, null);
        BmpSRC = zoomImg(BmpSRCOLD,dp2px(200),dp2px(200));
        mItemWaveLength = BmpDST.getWidth();
        startAnim();
    }

    public Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }
    private int dp2px(int dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //先画上圆形
        canvas.drawBitmap(BmpSRC, 0, 0, mPaint);
        //再画上结果
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //第一个rect是裁剪原图的区域 如果为null表示不裁剪
        //第二个rect是裁剪完原图剩下的图 ：处理后的图片需要绘制到View控件上的哪个区域，
        // 图片小于指定区域-放大；图片大于指定区域-缩小

        canvas.drawBitmap(BmpDST, new Rect(dx, 0, dx + BmpSRC.getWidth(), BmpSRC.getHeight()), new Rect(0, 0, BmpSRC.getWidth(), BmpSRC.getHeight()), mPaint);
        Log.i("zjc",dx+" ");
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(BmpSRC, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }


    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(4000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

}
