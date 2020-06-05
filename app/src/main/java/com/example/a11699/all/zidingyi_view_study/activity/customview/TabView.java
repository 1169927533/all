package com.example.a11699.all.zidingyi_view_study.activity.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.a11699.all.R;

/**
 * Create time 2020/3/27
 * Create Yu
 */
public class TabView extends View {
    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    Paint mDstPaint, mSrcPaint;
    Bitmap mSrcBitmap,mDstBitmap;
    Canvas mSrcCanvas,mDstCanvas;

    void initView() {
        mDstPaint = new Paint();
        mSrcPaint = new Paint();
        mDstPaint.setColor(Color.YELLOW);
        mSrcPaint.setColor(Color.BLUE);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mSrcBitmap = Bitmap.createBitmap(500, 900, Bitmap.Config.ARGB_8888);
        mSrcCanvas = new Canvas(mSrcBitmap);

        mDstBitmap = Bitmap.createBitmap(500,900,Bitmap.Config.ARGB_8888);
        mDstCanvas = new Canvas(mDstBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(100, 100, 500, 500);
        Paint mDpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint mSpaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        mDpaint.setColor(getResources().getColor(R.color.color_6c00ff));
        mDstCanvas.drawColor(Color.GRAY);
        mDstCanvas.drawRoundRect(rectF, 30, 30, mDpaint);//根据提供的矩形为四个角画弧线，（其中的数字：第一个表示X轴方向大小，第二个Y轴方向大小。可以改成其他的，你可以自己体验），最后添加画笔。
        canvas.drawBitmap(mDstBitmap,0,0,mDpaint);



        mSpaint.setColor(getResources().getColor(R.color.colorPink));
        mSpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.translate(0, 130);
        mSrcCanvas.drawColor(Color.RED);
        mSrcCanvas.drawRect(rectF, mSpaint);
        canvas.drawBitmap(mSrcBitmap,0,0,mSpaint);



      /*  //dst
        mDstCanvas.drawRect(20,20,80,80,mDstPaint);
        canvas.drawBitmap(mDstBitmap,0,0,mDstPaint);
        //src
        mSrcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        mSrcCanvas.drawCircle(25,25,25,mSrcPaint);
        canvas.drawBitmap(mSrcBitmap,0,0,mSrcPaint);*/
    }
}
