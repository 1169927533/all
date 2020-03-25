package com.example.a11699.all.shuibowen;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.a11699.all.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Create time 2020/3/23
 * Create Yu
 */
public class SpreadView extends View {
    private Paint centerPaint; //中心圆paint
    private int radius = 100; //中心圆半径
    private Paint spreadPaint; //扩散圆paint
    private Paint spreadPaint_Stroke;//扩散的边线
    private float centerX;//圆心x
    private float centerY;//圆心y
    private int distance = 15; //每次圆递增间距
    private int maxRadius = 80; //最大圆半径
    private int delayMilliseconds = 1183;//扩散延迟间隔，越大扩散越慢
    private List<Integer> spreadRadius = new ArrayList<>();//扩散圆层级数，元素为扩散的距离
    private List<Integer> alphas = new ArrayList<>();//对应每层圆的透明度

    public SpreadView(Context context) {
        this(context, null, 0);
    }

    public SpreadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpreadView(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SpreadView, defStyleAttr, 0);
        radius = a.getInt(R.styleable.SpreadView_spread_radius, radius);
        maxRadius = a.getInt(R.styleable.SpreadView_spread_max_radius, maxRadius);
        int centerColor = a.getColor(R.styleable.SpreadView_spread_center_color, ContextCompat.getColor(context, R.color.color_6c00ff));
        int spreadColor = a.getColor(R.styleable.SpreadView_spread_spread_color, ContextCompat.getColor(context, R.color.color_6c00ff));
        distance = a.getInt(R.styleable.SpreadView_spread_distance, distance);
        a.recycle();
        centerPaint = new Paint();
        centerPaint.setColor(centerColor);
        centerPaint.setAntiAlias(true);
        //最开始不透明且扩散距离为0
        alphas.add(255);
        spreadRadius.add(0);
        spreadPaint = new Paint();
        spreadPaint.setAntiAlias(true);
        spreadPaint.setAlpha(255);
        spreadPaint.setColor(spreadColor);

        spreadPaint_Stroke = new Paint();
        spreadPaint_Stroke.setAntiAlias(true);
        spreadPaint_Stroke.setAlpha(255);
        spreadPaint_Stroke.setStyle(Paint.Style.STROKE);
        spreadPaint_Stroke.setStrokeWidth(3);
        spreadPaint_Stroke.setColor(getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //圆心位置
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < spreadRadius.size(); i++) {
            int alpha = alphas.get(i);
            spreadPaint.setAlpha(alpha);
            spreadPaint_Stroke.setAlpha(alpha);
            int width = spreadRadius.get(i);
            //绘制扩散的圆
            canvas.drawCircle(centerX, centerY, radius + width, spreadPaint);
            //  canvas.drawCircle(centerX, centerY, radius + width, spreadPaint_Stroke);//画的是边界
            //每次扩散圆半径递增，圆透明度递减
            alpha = alpha - distance > 0 ? alpha - distance : 0;
            alphas.set(i, alpha);
            spreadRadius.set(i, width + distance + 5);
        }
        //当最外层扩散圆半径达到最大半径时添加新扩散圆
        if (spreadRadius.get(spreadRadius.size() - 1) > maxRadius + 20) {
            spreadRadius.add(0);
            alphas.add(255);
        }
        //超过8个扩散圆，删除最先绘制的圆，即最外层的圆
        if (spreadRadius.size() >= 8) {
            alphas.remove(0);
            spreadRadius.remove(0);
        }
        //中间的圆
        canvas.drawCircle(centerX, centerY, radius, centerPaint);

        //延迟更新，达到扩散视觉差效果
        postInvalidateDelayed(delayMilliseconds);
    }


    int dpToPx(int dps) {
        return Math.round(getResources().getDisplayMetrics().density * dps);
    }
}
