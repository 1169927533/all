package com.example.a11699.all.zhizhuwang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 作者：余智强
 * 2019/5/8
 */
public class SpiderView extends View {
    private Paint radarPaint, valuePaint;
    private Paint rePaint;
    private float radius;//网格最大半径
    private int centerX;//中心X
    private int centerY;//中心Y
    String TAG = "spliderview";
    private int count = 6;//网的层数

    public SpiderView(Context context) {
        super(context);
        init();
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "构造函数执行");
        init();
    }

    void init() {

        radarPaint = new Paint();
        radarPaint.setStyle(Paint.Style.FILL_AND_STROKE );
        radarPaint.setColor(Color.GREEN);

        valuePaint = new Paint();
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        rePaint = new Paint();
        rePaint.setStyle(Paint.Style.STROKE);
        rePaint.setColor(Color.RED);
        rePaint.setStrokeWidth(10);

    }
int colord[] = {Color.RED,Color.BLACK};

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(TAG, "onSizeChanged执行");
        radius = Math.min(h, w) / 2 * 0.9f;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();

        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw执行");
        canvas.drawPoint(centerX, centerY, rePaint);
        Log.i(TAG, "1:  centerX:" + centerX + "centerY:" + centerY);
        //绘制蜘蛛网格
        drawPolygon(canvas);
        //画网格中线
        drawLines(canvas);
        //画数据图
   //     drawRegion(canvas);
    }

    void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count);
        for (int i = count; i >= 1; i--) {
            float curR = r * i;
             path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    float x = (float) (centerX + curR * Math.cos((Math.PI * 60 / 180) * j));
                    float y = (float) (centerY + curR * Math.sin((Math.PI * 60 / 180) * j));
                    path.lineTo(x, y);
                }

            }

            path.close();
          //  radarPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
            radarPaint.setColor(colord[i%2]);
            canvas.drawPath(path, radarPaint);
        }
    }

    void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos((Math.PI * 60 / 180) * i));
            float y = (float) (centerY + radius * Math.sin((Math.PI * 60 / 180) * i));
            path.lineTo(x, y);
            canvas.drawPath(path, radarPaint);
        }
    }

    private double[] data = {2, 5, 1, 6, 4, 5};
    private float maxValue = 6;

    void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(127);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos((Math.PI * 60 / 180) * i) * percent);
            float y = (float) (centerY + radius * Math.sin((Math.PI * 60 / 180) * i) * percent);
            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x, y, 10, valuePaint);
        }
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }
}
