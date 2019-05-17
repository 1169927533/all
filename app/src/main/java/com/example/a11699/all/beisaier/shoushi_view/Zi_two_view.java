package com.example.a11699.all.beisaier.shoushi_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.a11699.all.R;

/**
 * 作者：余智强
 * 2019/4/30
 */
public class Zi_two_view extends View {
    private Path mPath = new Path();
    private Paint mPaint;
    int screenWidth;//转换成dp的宽度
    int width;//屏幕的宽度

    private PointF start_point, end_point, control_point;//贝塞尔曲线的起始点 终止点 控制点
    float moren_hight = 380;//贝塞尔曲线的高度


    public Zi_two_view(Context context) {
        super(context);
    }


    public Zi_two_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setARGB(255,174 ,238, 238);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(5);

        initPoint();

    }

    void initPoint() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;         // 屏幕宽度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        screenWidth = (int) (width / density);  // 屏幕宽度(dp)

        start_point = new PointF(0, 200);
        end_point = new PointF(width, 200);
        control_point = new PointF(width / 2, moren_hight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(start_point.x, start_point.y);

        moren_hight = moren_hight - bianhua;
        if (moren_hight > 380) {
            moren_hight = 380;
        } else if (moren_hight <= 200) {
            moren_hight = 200;
        }

        mPath.quadTo(width / 2, moren_hight, end_point.x, end_point.y);
        mPath.lineTo(width, 0);
        mPath.lineTo(0, 0);


        canvas.drawPath(mPath, mPaint);

    }

    float x1 = 0, y1 = 0, x2 = 0, y2 = 300;
    float bianhua = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //当手指按下的时候
                x1 = event.getX();
                y1 = event.getY();

                return true;

            case MotionEvent.ACTION_MOVE:
                x2 = event.getX();
                y2 = event.getY();

                if (y2 - y1 > 0) {
                    //向下滑
                    bianhua = -(y2 - y1);
                } else {
                    //向上滑
                    bianhua = y1 - y2;
                }

                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

}
