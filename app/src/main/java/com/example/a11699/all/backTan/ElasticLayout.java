package com.example.a11699.all.backTan;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

import feign.Util;

/**
 * Create time 2020/1/20
 * Create Yu
 */
public class ElasticLayout extends LinearLayout {
    Scroller mScroller;//滚动器
    ScrollEndScrollView scrollView;//最终内容还是包裹在ScrollView里面的
    Boolean isToBottom = false, isToTop = false;

    public ElasticLayout(Context context) {
        this(context, null);
    }

    public ElasticLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ElasticLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);//设置Linearlayout的布局方向
        mScroller = new Scroller(context);
        initScrollView();
    }

    private void initScrollView() {
        scrollView = new ScrollEndScrollView(getContext());
        scrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);//设置滑动到边线的光宣效果 OVER_SCROLL_NEVER表示无效果
        scrollView.setVerticalFadingEdgeEnabled(false);
        //设置顶部和底部的渐变效果 false表示不启动渐变
        scrollView.addOnScrollEndListener(new ScrollEndScrollView.OnScrollerEndListener() {
            @Override
            public void scrollToBottom(View view) {
                isToBottom = true;
            }

            @Override
            public void scrollToTop(View view) {
                isToTop = true;
            }

            @Override
            public void scrollToMiddle(View view) {
                isToTop = false;
                isToBottom = false;
            }
        });
    }

    int mMaxScrollY;
    boolean isOverScreen;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMaxScrollY = getMeasuredHeight() * 3 / 5;
        String content = "scrollView.getMeasuredHeight() = " + scrollView.getMeasuredHeight() + "  MeasureSpec.getSize(heightMeasureSpec)=" + MeasureSpec.getSize(heightMeasureSpec);
        prient(content);
        isOverScreen = !(scrollView.getMeasuredHeight() < MeasureSpec.getSize(heightMeasureSpec));

    }

    void prient(String content) {
        Log.i("zjc", content);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount == 1) {
            View child = getChildAt(0);
            removeView(child);
            scrollView.addView(child);
            addView(scrollView);
        } else if (childCount > 1) {
            throw new IllegalStateException("不允许存放多个子View");
        }
    }

    protected int mMoveY;
    protected int mLastY;

    //只有当onInterceptTouchEvent返回true表示拦截事件 onTouchEvent才会响应
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        int yPosition = (int) ev.getY();//获取点击事件距离控件顶边的距离，即视图坐标  越往上数值越小


        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScroller.abortAnimation();//停止
                mLastY = yPosition;
                mMoveY = 0;
                String content2 = "屏幕按下时促发：" + "mLastY: " + mLastY + "  mMoveY: " + mMoveY;

                break;
            case MotionEvent.ACTION_MOVE:
                mMoveY = (mLastY - yPosition);//当手指向上滑动 则mMoveY为负值
                mLastY = yPosition;
                String content3 = "移动时促发：" + "mMoveY: " + mMoveY + "  mLastY: " + mLastY;

                if (isToTop) {
                    if (mMoveY < 0) {
                       //mMoveY为负值表示屏幕内容在向上滑
                        return true;
                    }
                } else if (isToBottom) {
                    if (mMoveY > 0) {
                        //向上
                        return true;
                    }
                }
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        int yPosition = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScroller.abortAnimation();
                mLastY = yPosition;
                mMoveY = 0;
                return true;
            case MotionEvent.ACTION_MOVE:
                mMoveY = (mLastY - yPosition);
                mLastY = yPosition;

             //手指由上向下 mMoveY小于0
                if (isToTop) {
                    if (mMoveY < 0) {
                        //向下
                        smoothScrollBy(0, mMoveY / 2);
                        return true;
                    } else {
                        prient("执行了向上回弹");
                        //向上
                        prient("向上回弹 mScroller.getFinalY( : "+mScroller.getFinalY());
                        if (mScroller.getFinalY() < 0) {//返回滚动结束的位置
                            smoothScrollBy(0, mMoveY / 4);
                            return true;
                        } else {
                            prient("向上回弹窝头了");
                            smoothScrollTo(0, 0);
                        }
                    }
                } else if (isToBottom) {
                    if (mMoveY > 0) {
                        //向上
                        smoothScrollBy(0, mMoveY / 2);
                        return true;
                    } else {

                        //向上
                        prient("向下回弹： mScroller.getFinalY( : "+mScroller.getFinalY());
                        //向下
                        if (mScroller.getFinalY() != 0) {
                            if (getScrollY() + mMoveY / 2 > 0) {
                                smoothScrollBy(0, mMoveY / 2);
                                return true;
                            } else {
                                smoothScrollTo(0, 0);
                            }
                        }
                    }
                }

           //     smoothScrollTo(0, 0);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isToBottom || isToTop) {
                    //回弹
                    smoothScrollTo(0, 0);
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {
            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
    }


    //调用此方法滚动到目标位置
    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }

    //调用此方法设置滚动的相对偏移
    public void smoothScrollBy(int dx, int dy) {
        if (dy > 0)
            dy = Math.min(dy, mMaxScrollY);
        else
            dy = Math.max(dy, -mMaxScrollY);
        //设置mScroller的滚动偏移量
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy, Math.max(300, Math.abs(dy)));
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }
}
