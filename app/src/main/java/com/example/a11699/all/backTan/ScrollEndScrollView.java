package com.example.a11699.all.backTan;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Create time 2020/1/20
 * Create Yu
 */
public class ScrollEndScrollView extends ScrollView {
    OnScrollerEndListener onScrollerEndListener;
    boolean isTop = false, isBottom = false;

    public ScrollEndScrollView(Context context) {
        super(context);
    }

    public ScrollEndScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollEndScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(isTop){
            return false;
        }
        if(isBottom){
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //l是当前的水平滚动距离
        //t是竖直方向的滚动距离
        if (t == 0) {
            if (null != onScrollerEndListener) {
                isTop = true;
                isBottom = false;
                onScrollerEndListener.scrollToTop(this);
            }
        } else if (t + getMeasuredHeight() >= getChildAt(0).getMeasuredHeight()) {
            if (null != onScrollerEndListener) {
                isTop = false;
                isBottom = true;
                onScrollerEndListener.scrollToBottom(this);
            }
        } else {
            if (null != onScrollerEndListener) {
                isTop = false;
                isBottom = false;
                onScrollerEndListener.scrollToMiddle(this);
            }
        }
    }

    public void addOnScrollEndListener(OnScrollerEndListener onScrollerEndListener) {
        this.onScrollerEndListener = onScrollerEndListener;
    }

    public interface OnScrollerEndListener {
        void scrollToBottom(View view);//滚动到底部

        void scrollToTop(View view);//滚动到顶部

        void scrollToMiddle(View view);//滚动到中间
    }
}
