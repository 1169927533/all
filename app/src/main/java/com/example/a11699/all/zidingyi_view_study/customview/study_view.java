package com.example.a11699.all.zidingyi_view_study.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：余智强
 * 2019/3/6
 */
public class study_view extends View {
    public study_view(Context context) {
        super(context,null);

    }

    public study_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public study_view(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
        canvas.drawCircle(190,200,150,paint);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(180,200,100,paint);
        canvas.drawRGB(0xff,0xff,0xff);
        Color.argb(1,1,1,1);
        canvas.drawPath(new Path(),paint);
        Path path = new Path();
        path.moveTo(10,10);

    }

    /*  @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100,widthMeasureSpec);
        int height = getMySize(100,heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }
        //设置view的高宽度
        setMeasuredDimension(width, height);
    }

    //设置view的宽高度
    private int getMySize(int defaultSize,int meansureSpec){
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(meansureSpec);//获取测量模式
        int size = MeasureSpec.getSize(meansureSpec);

        switch (mode){
            case MeasureSpec.UNSPECIFIED:{//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST:{//如果测量模式是最大取值为size
                //将大小取最大值，
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY:{//如果是固定大小，那就不用去改变
                mySize = size;
                break;
            }
        }
        return mySize;
    }*/
}
