package com.example.a11699.all.huizhixiaozhu.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.a11699.all.huizhixiaozhu.object.ViewPath;
import com.example.a11699.all.huizhixiaozhu.object.ViewPathEvaluator;
import com.example.a11699.all.huizhixiaozhu.object.ViewPoint;

/**
 * 作者：余智强
 * 2019/3/7
 */
public class PageView extends View {
    /**
     * 画猪鼻子
     */
    private RectF rect;
    private Paint paintPink;
    private Paint paintRed = new Paint();
    private Paint paintBlack = new Paint();
    private Paint paintWhite = new Paint();


    boolean isInitPath = false;

    //常规百分百绘制
    private ValueAnimator animNose;
    private ValueAnimator animEyes;
    private ValueAnimator animFace;//腮红
    private ValueAnimator animMouth;
    private ValueAnimator animLegs;
    private ValueAnimator animFoots;

    private int progressNose = 0;
    private int progressEyes = 0;
    private int progressFace = 0;
    private int progressMouth = 0;
    private int progressLegs = 0;
    private int progressFoots = 0;

    private Path mPath = new Path();
    private Path mPathEar1 = new Path();
    private Path mPathEar2 = new Path();
    private Path mPathBody = new Path();
    private Path mPathArmRight = new Path();
    private Path mPathHandRight = new Path();
    private Path mPathArmLeft = new Path();
    private Path mPathHandLeft = new Path();
    private Path mPathTail = new Path();

    private ViewPoint pointHead = new ViewPoint();
    private ViewPoint pointEar1 = new ViewPoint();
    private ViewPoint pointEar2 = new ViewPoint();
    private ViewPoint pointBody = new ViewPoint();
    private ViewPoint pointArmRight = new ViewPoint();
    private ViewPoint pointHandRight = new ViewPoint();
    private ViewPoint pointArmLeft = new ViewPoint();
    private ViewPoint pointHandLeft = new ViewPoint();
    private ViewPoint pointTail = new ViewPoint();

    // 贝塞尔曲线绘制
    private ValueAnimator animHead;
    private ValueAnimator animEar1;
    private ValueAnimator animEar2;
    private ValueAnimator animBody;
    private ValueAnimator animArmRight;
    private ValueAnimator animHandRight;
    private ValueAnimator animArmLeft;
    private ValueAnimator animHandLeft;
    private ValueAnimator animTail;
    public PageView(Context context) {
        super(context);
        init();
    }

    public PageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        //初始化猪鼻子画的矩形区域
        rect = new RectF();
        //创建画笔
        paintPink = new Paint();
        //设置画笔的颜色
        paintPink.setColor(Color.rgb(255,155,192));
        //设置画笔的填充方式:描边
        paintPink.setStyle(Paint.Style.STROKE);
        //设置画笔的宽度
        paintPink.setStrokeWidth(3f);
        //设置抗锯齿，让变更圆润些
        paintPink.setAntiAlias(true);

        paintRed.setColor(Color.rgb(255,99,71));
        paintRed.setStyle(Paint.Style.STROKE);
        paintRed.setStrokeWidth(3f);
        paintRed.setAntiAlias(true);

        paintBlack.setColor(Color.BLACK);
        paintBlack.setStyle(Paint.Style.STROKE);
        paintBlack.setStrokeWidth(3f);
        paintBlack.setAntiAlias(true);

        paintWhite.setColor(Color.WHITE);
        paintBlack.setStyle(Paint.Style.STROKE);
        paintBlack.setStrokeWidth(3f);
        paintBlack.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(widthMeasureSpec > 0){
            if(!isInitPath){
                isInitPath = true;
                initIntAnim();
                initpath();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    //初始化动画 这个动画是让画的圆是闭合的
    void initIntAnim(){
        //鼻子
        animNose = ValueAnimator.ofInt(0,100);//动画变化区间
        animNose.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //监听动画进度变化，并执行重回操作 就是获取变化的值
                progressNose = (int)animation.getAnimatedValue();//表示当仅有一个属性处于动画状态时，由该ValueAnimator计算的当前属性值
                initIntAnim();
            }
        });
        animNose.setDuration(1000);

        animEyes = ValueAnimator.ofInt(0,100);
        animEyes.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressEyes = (int)animation.getAnimatedValue();
                initIntAnim();
            }
        });
        animEyes.setDuration(800);

        animFace = ValueAnimator.ofInt(0,100);
        animFace.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressFace = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animFace.setDuration(800);

        // 嘴巴
        animMouth = ValueAnimator.ofInt(0, 100);
        animMouth.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressMouth = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animMouth.setDuration(500);

        // 腿
        animLegs = ValueAnimator.ofInt(0, 100);
        animLegs.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressLegs = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animLegs.setDuration(400);

        // 脚
        animFoots = ValueAnimator.ofInt(0, 100);
        animFoots.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressFoots = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animFoots.setDuration(400);
    }

    //初始化路径
    void initpath(){
        ViewPath viewPath = new ViewPath();
        pointHead.x = dp2px(220);
        pointHead.y = dp2px(102);
        mPath.moveTo(pointHead.x,pointHead.y);
        viewPath.moveTo(pointHead.x,pointHead.y);//保存每次移动的点坐标
        viewPath.curveTo(dp2px(-100), dp2px(80), dp2px(130), dp2px(330), dp2px(170), dp2px(170));
        viewPath.quadTo(dp2px(210), dp2px(170), dp2px(240), dp2px(155));

        animHead = ValueAnimator.ofObject(new ViewPathEvaluator(), viewPath.getPoints().toArray());

    }


    private int dp2px(float dpValue) {
        return dp2px(getContext(), dpValue);
    }

    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
