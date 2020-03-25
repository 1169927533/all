package com.example.a11699.all.shuibowen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;

import com.example.a11699.all.R;


/**
 * Create time 2020/3/14
 * Create Yu
 */
public class RadarView extends View {
    int realWidth;
    int realHeight;
    int ccc = 120;

    private Context mContext;

    private Paint mPaintLine;
    private Paint mPaintCircle;
    private Paint mPaintSector;
    public boolean isstart = false;
    private ScanThread mThread;
    private Paint mPaintPoint;
    //旋转效果起始角度
    private int start = 0;

    private Shader mShader;

    private Matrix matrix;

    public final static int CLOCK_WISE = 1;
    public final static int ANTI_CLOCK_WISE = -1;

    @IntDef({CLOCK_WISE, ANTI_CLOCK_WISE})
    public @interface RADAR_DIRECTION {

    }

    //默认为顺时针呢
    private final static int DEFAULT_DIERCTION = CLOCK_WISE;

    //设定雷达扫描方向
    private int direction = DEFAULT_DIERCTION;

    private boolean threadRunning = true;

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint(attrs);
    }

    public RadarView(Context context) {
        this(context, null);
    }

    private void initPaint(AttributeSet attrs) {
        setBackgroundColor(Color.TRANSPARENT);

        //宽度=5，抗锯齿，描边效果的浅绿色画笔
        mPaintCircle = new Paint();
        mPaintCircle.setStrokeWidth(5);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStyle(Paint.Style.FILL);
        mPaintCircle.setColor(0x99000000);

        //暗绿色的画笔
        mPaintSector = new Paint();
        mPaintSector.setColor(0x9D00ff00);
        mPaintSector.setAntiAlias(true);

    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //度量和保存自己
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);//viewgroup解析的宽度
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);//viewgroup解析的高度
        int parentNeedWidth = 100;
        int parentNeedHeight = 100;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeedWidth;
        realHeight = (heightMode == MeasureSpec.EXACTLY) ? selfHeight : parentNeedHeight;
        mShader = new SweepGradient(realWidth / 2, realHeight / 2, getResources().getColor(R.color.color_676BBC), Color.TRANSPARENT);
        mPaintSector.setShader(mShader);
        setMeasuredDimension(realWidth, realHeight);
    }

    public void start() {
        mThread = new ScanThread(this);
        mThread.setName("radar");
        mThread.start();
        threadRunning = true;
        isstart = true;
    }

    public void stop() {
        if (isstart) {
            threadRunning = false;
            isstart = false;
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        //根据matrix中设定角度，不断绘制shader,呈现出一种扇形扫描效果
        ccc += 3;
        if (ccc >= 600) {
            ccc = 600;
        }
        canvas.concat(matrix);
        canvas.drawCircle(realWidth / 2, realHeight / 2, ccc, mPaintSector);
        super.onDraw(canvas);
    }

    public void setDirection(@RADAR_DIRECTION int direction) {
        if (direction != CLOCK_WISE && direction != ANTI_CLOCK_WISE) {
            throw new IllegalArgumentException("Use @RADAR_DIRECTION constants only!");
        }
        this.direction = direction;
    }

    protected class ScanThread extends Thread {

        private RadarView view;

        public ScanThread(RadarView view) {
            // TODO Auto-generated constructor stub
            this.view = view;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (threadRunning) {
                if (isstart) {
                    view.post(new Runnable() {
                        public void run() {
                            start = start + 1;
                            matrix = new Matrix();
                            matrix.preRotate(direction * start, realWidth / 2, realHeight / 2);
                            view.invalidate();

                        }
                    });
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}