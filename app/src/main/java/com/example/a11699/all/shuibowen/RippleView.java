package com.example.a11699.all.shuibowen;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.a11699.all.R;
import com.example.a11699.all.dialogment.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：余智强
 * 2019/5/9
 */
public class RippleView extends View {

    private Context mContext;

    private Paint mPaint;

    //VIew宽度
    private float mWidth;

    //VIew高度
    private float mHight;

    //声波的圆圈集合
    private List<Circle> mRipples;

    private int sqrtNumber;

    //圆圈扩散的速度
    private int mSpeed;

    //圆圈之间的密度
    private int mDensity;

    //圆圈的颜色
    private int mColor;

    //圆圈是否为填充模式
    private boolean mIsFill;

    // 圆圈是否为渐变模式
    private boolean mIsAlpha;

    public RippleView(Context context) {
        this(context,null);
    }

    public RippleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //  获取用户配置属性
        TypedArray tya = context.obtainStyledAttributes(attrs, R.styleable.mRippleView);
        mColor = tya.getColor(R.styleable.mRippleView_cColor, Color.BLUE);
        mSpeed = tya.getInt(R.styleable.mRippleView_cSpeed,1);
        mDensity = tya.getInt(R.styleable.mRippleView_cDensity, 10);
        mIsFill = tya.getBoolean(R.styleable.mRippleView_cIsFill, false);
        mIsAlpha = tya.getBoolean(R.styleable.mRippleView_cIsAlpha, false);
        tya.recycle();//回收
        init();

    }
    void init(){
        mContext = getContext();
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(DensityUtil.dip2px(1,mContext));
        if(mIsFill){
            mPaint.setStyle(Paint.Style.FILL);
        }else{
            mPaint.setStyle(Paint.Style.STROKE);
        }
        //Cap.ROUND(圆形线冒)、Cap.SQUARE(方形线冒)、Paint.Cap.BUTT(无线冒)
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置画笔是否抗锯齿
        mPaint.setAntiAlias(true);
        //添加第一个圆圈
        mRipples = new ArrayList<>();
        Circle circle = new Circle(0,255);
        mRipples.add(circle);

        mDensity = DensityUtil.dip2px(mDensity,mContext);

        //设置view的圆半透明
        setBackgroundColor(Color.TRANSPARENT);
    }
    class Circle{
        int width;
        int alpha;
        Circle(int width,int alpha){
            this.width = width;
            this.alpha = alpha;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //内切正方形
        drawInCircle(canvas);

        // 外切正方形
        // drawOutCircle(canvas);
    }
    private void drawInCircle(Canvas canvas){
        canvas.save();
        //处理每个圆的宽度和透明度
        for (int i = 0 ; i < mRipples.size() ; i++) {
            Circle c = mRipples.get(i);
            mPaint.setAlpha(c.alpha);
            canvas.drawCircle(mWidth/2,mHight/2,c.width-mPaint.getStrokeWidth(),mPaint);
            //当圆超出view的宽度后删除
            if( c.width > mWidth / 2){
                mRipples.remove(i);
            }else{
                // 计算不透明的数值，这里有个小知识，就是如果不加上double的话，255除以一个任意比它大的数都将是0
                if (mIsAlpha) {
                    double alpha = 255 - c.width * (255 / ((double) mWidth / 2));
                    c.alpha = (int) alpha;
                }
                // 修改这个值控制速度
                c.width += mSpeed;
            }
        }
        //里面添加圆
        if(mRipples.size()>0){
            //控制第二个圆出来的间距
            if (mRipples.get(mRipples.size() - 1).width > DensityUtil.dip2px( mDensity,mContext)) {
                mRipples.add(new Circle(0, 255));
            }
        }
        invalidate();
        canvas.restore();
    }
    void d(int a){

    }
    void d(String c){

    }
    /**
     * 圆到对角线
     *
     * @param canvas
     */
    private void drawOutCircle(Canvas canvas) {
        canvas.save();
        // 使用勾股定律求得一个外切正方形中心点离角的距离
        sqrtNumber = (int) (Math.sqrt(mWidth * mWidth + mHight * mHight) / 2);

        // 变大
        for (int i = 0; i < mRipples.size(); i++) {

            // 启动圆圈
            Circle c = mRipples.get(i);
            mPaint.setAlpha(c.alpha);// （透明）0~255（不透明）
            canvas.drawCircle(mWidth / 2, mHight / 2, c.width - mPaint.getStrokeWidth(), mPaint);

            // 当圆超出对角线后删掉
            if (c.width > sqrtNumber) {
                mRipples.remove(i);
            } else {
                // 计算不透明的度数
                double degree = 255 - c.width * (255 / (double) sqrtNumber);
                c.alpha = (int) degree;
                c.width += 1;
            }
        }

        // 里面添加圆
        if (mRipples.size() > 0) {
            // 控制第二个圆出来的间距
            if (mRipples.get(mRipples.size() - 1).width == 50) {
                mRipples.add(new Circle(0, 255));
            }
        }
        invalidate();
        canvas.restore();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int myWidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int myWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int myHeightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int myHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        // 获取宽度
        if (myWidthSpecMode == MeasureSpec.EXACTLY) {
            // match_parent
            mWidth = myWidthSpecSize;
        } else {
            // wrap_content
            mWidth = DensityUtil.dip2px(120,mContext);
        }

        // 获取高度
        if (myHeightSpecMode == MeasureSpec.EXACTLY) {
            mHight = myHeightSpecSize;
        } else {
            // wrap_content
            mHight = DensityUtil.dip2px(120,mContext);
        }

        // 设置该view的宽高
        setMeasuredDimension((int) mWidth, (int) mHight);
    }

}
