package com.example.a11699.all.wujiaoxing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import com.example.a11699.all.R;

/**
 * 作者：余智强
 * 2019/5/16
 */
public class CustomView extends View {
    Paint mPaint;
    Path mPath;
    int myHight;//整个控件的高度
    int myWidth;//整个空间的宽度
    int wu_R;//五角星的大半径
    int wu_r;//五角星的小半径
    boolean isfill;//是否填充
    int mcolor;//五角星背景
    int mWraduis;//五角星圆润度
    int mWNumber;//五角星的数量

    //星星点击事件接口
    private OnItemSelectedListener onItemSelectedListener;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPath = new Path();


        TypedArray tya = context.obtainStyledAttributes(attrs, R.styleable.mWujiaoxing);
        wu_R = tya.getInt(R.styleable.mWujiaoxing_mR, 20);
        wu_r = tya.getInt(R.styleable.mWujiaoxing_mr, 10);
        isfill = tya.getBoolean(R.styleable.mWujiaoxing_mIsFill, false);
        mcolor = tya.getColor(R.styleable.mWujiaoxing_mWuColor, 0xff00ff);
        mWraduis = tya.getInteger(R.styleable.mWujiaoxing_mWRaduis,0);
        mWNumber = tya.getInteger(R.styleable.mWujiaoxing_mWNumber,5);
        tya.recycle();//回收0x
        mPaint.setColor(mcolor);
        mPaint.setPathEffect(new CornerPathEffect(mWraduis));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawStar(wu_R, wu_r, myWidth / 2, myHight / 2);

        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        myWidth = getMySize(100, widthMeasureSpec);
        myHight = getMySize(100, heightMeasureSpec);
        setMeasuredDimension(myWidth, myHight);
    }

    int getMySize(int defaultSize, int meansureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(meansureSpec);//获得测量模式
        int size = MeasureSpec.getSize(meansureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST://wrap
                mySize = size;
                break;
            case MeasureSpec.EXACTLY://100dp 或者 Match
                mySize = size;
                break;
        }
        return mySize;
    }

    void drawStar(int r, int R, int x, int y) {
        //mPath.reset();
        mPath.moveTo((float) (Math.cos(Math.PI * (18 + 72 * 0) / 180) * R + x), (float) (Math.sin(Math.PI * (18 + 72 * 0) / 180) * R + y));
        mPath.lineTo((float) (Math.cos(Math.PI * (54 + 72 * 0) / 180) * r + x), (float) (Math.sin(Math.PI * (54 + 72 * 0) / 180) * r + y));
        for (int i = 1; i < 5; i++) {
            mPath.lineTo((float) (Math.cos(Math.PI * (18 + 72 * i) / 180) * R + x), (float) (Math.sin(Math.PI * (18 + 72 * i) / 180) * R + y));
            mPath.lineTo((float) (Math.cos(Math.PI * (54 + 72 * i) / 180) * r + x), (float) (Math.sin(Math.PI * (54 + 72 * i) / 180) * r + y));
        }
        mPath.close();
        if (isfill) {
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onItemSelectedListener.onItemSelect("星星被点击了");
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    //星星的点击事件
    public interface OnItemSelectedListener {
        public void onItemSelect(String content);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }
}
