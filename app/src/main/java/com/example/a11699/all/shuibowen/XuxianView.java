package com.example.a11699.all.shuibowen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Create time 2020/3/23
 * Create Yu
 */
public class XuxianView extends View {
    Paint paint;
    int phase = 43;

    public XuxianView(Context context) {
        this(context, null);
    }

    public XuxianView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XuxianView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        paint = new Paint();
        paint.setStrokeWidth(4);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        paint.setColor(Color.BLUE);
        canvas.drawPoint(200, 10, paint);


        Path path = new Path();
        path.moveTo(100, 300);
        path.lineTo(300, 100);
        path.lineTo(400, 700);

        canvas.drawPath(path, paint);
        paint.setColor(Color.RED);

//使用DashPathEffect画线段
        paint.setPathEffect(new DashPathEffect(new float[]{20, 10, 10, 5}, 0));
        canvas.translate(10, 0);
        canvas.drawPath(path, paint);

//画同一条线段,偏移值为15
        paint.setPathEffect(new DashPathEffect(new float[]{20, 10, 10, 5}, 15));
        paint.setColor(Color.BLUE);
        canvas.translate(10, 0);
        canvas.drawPath(path, paint);


        //默认43
        paint.setPathEffect(new DashPathEffect(new float[]{25, 3, 5, 10}, phase));
        canvas.drawPath(path, paint);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        phase--;
        if (phase <= 0) phase = 43;
        postInvalidate();
    }
}
