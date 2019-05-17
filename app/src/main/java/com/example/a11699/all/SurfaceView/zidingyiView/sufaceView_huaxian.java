package com.example.a11699.all.SurfaceView.zidingyiView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class sufaceView_huaxian extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mSyrfaceHolder;
    //绘图的Canvas
    private Canvas mCanvas;
    //子线程标志
    private boolean mIsDrawing;
    private int x = 0, y = 0;//图像的位置
    private Paint mPaint;//画笔
    private Path mPath;//画的位置

    //********************这时继承Surfaceview实现的方法************************************
    public sufaceView_huaxian(Context context) {
        super(context, null);
    }

    public sufaceView_huaxian(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public sufaceView_huaxian(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
        //路经的起始位置
        mPath.moveTo(0, 100);
        initview();
    }

    //初始化view
    void initview() {
        mSyrfaceHolder = getHolder();
        mSyrfaceHolder.addCallback(this);
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);
    }
    //画
    void drawSomething(){
        mCanvas = mSyrfaceHolder.lockCanvas();//获得canvas对象
        mCanvas.drawColor(Color.WHITE);//绘制背景
        mCanvas.drawPath(mPath,mPaint);
        if(mCanvas!=null){
            //  //释放canvas对象并提交画布
            mSyrfaceHolder.unlockCanvasAndPost(mCanvas);//提交画布
        }
    }

    //**************************************************************************************
    //*********************这是继承接口必须实现的方法*****************************************
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //创建
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //改变
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //销毁
        mIsDrawing = false;
    }

    @Override
    public void run() {
        //子线程
        while(mIsDrawing){
            drawSomething();
            x += 1;
            y = (int)(100 * Math.sin(2 * x * Math.PI / 180) + 400);
            //加入新的坐标点
            mPath.lineTo(x, y);
        }
    }
    //**************************************************************************************
}
