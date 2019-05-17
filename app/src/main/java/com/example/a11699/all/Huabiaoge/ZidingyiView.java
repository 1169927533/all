package com.example.a11699.all.Huabiaoge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * 作者：余智强
 * 2019/1/8
 */
public class ZidingyiView extends View {
    private Paint mGridPaint;//网格画笔
    private Point mWinSize;//屏幕尺寸
    private Point mCoo;//坐标系原点

    public ZidingyiView(Context context) {
        super(context, null);
        init();
    }

    public ZidingyiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        //准备屏幕尺寸
        mWinSize = new Point();
        mCoo = new Point(500, 500);
        loadWinSize(getContext(), mWinSize);
        mGridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * 获取屏幕高度
     *
     * @param ctx     上下文
     * @param winSize 屏幕尺寸
     */
    public static void loadWinSize(Context ctx, Point winSize) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(outMetrics);
        }
        winSize.x = outMetrics.widthPixels;
        winSize.y = outMetrics.heightPixels;
    }

    float sa[] = {0, 0};
    Canvas canvass;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvass = canvas;
        HelpDraw.drawGrid(canvas, mWinSize, mGridPaint);
        HelpDraw.drawCoo(canvas, mCoo, mWinSize, mGridPaint);
        drawPoint(sa, canvas);
    }

    public void drawColor() {
        canvass.drawRGB(224, 247, 245);
    }

    /**
     * 绘制点
     * @param
     */
    public void drawPoint(float[] dsd, Canvas canvas) {
        //绘制点
        canvas.drawPoint(100, 100, mGridPaint);
        //绘制一组点，坐标位置由float数组指定(必须是2的倍数个)
        canvas.drawPoints(dsd, mGridPaint);
    }

    public void set(float[] dsd) {
        this.sa = dsd;
       // invalidate();
    }
}
