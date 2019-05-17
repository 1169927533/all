package com.example.a11699.all.zidingyi_view_study.save_layer_study;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：余智强
 * 2019/5/7
 */
public class XfermodeView extends View {
    private int width = 400;
    private int height = 400;
    private Bitmap dstBmp;
    private Bitmap srcBmp;
    private Paint mPaint;

    public XfermodeView(Context context) {
        super(context);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        srcBmp = makeSrc(width,height);
        dstBmp = makeDst(width,height);
        mPaint = new Paint();
    }
    //创建一张矩形
    static Bitmap makeSrc(int w,int h){
        Bitmap bm = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFF66AAFF);
        c.drawRect(0,0,w,h,paint);
        return bm;
    }
    //圆
    static Bitmap makeDst(int w,int h){
        Bitmap bm = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0,0,w,h),paint);
        return bm;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);

        int layerID = canvas.saveLayer(0,0,width*2,height*2,mPaint,Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(dstBmp,0,0,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBmp,width/2,height/2,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerID);
    }
}
