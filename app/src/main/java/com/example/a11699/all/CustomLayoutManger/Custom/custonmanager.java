package com.example.a11699.all.CustomLayoutManger.Custom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.util.Pools;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：余智强
 * 2018/12/26
 */
public class custonmanager extends RecyclerView.LayoutManager {
    String TAGG = "zjc";
    int mTotalHeight;
    //手指 从上往下move是   下拉  dy是负
    //手指 从下往上move是   上拉  dy是正
    int mTheMoveDistance = 0;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    //onLayoutChildren每次初始化都会进行两次
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        int offsetY = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View scrap = recycler.getViewForPosition(i);
            addView(scrap);

            /*
             measureChildWithMargins ： 测量View,这个方法会考虑到View的ItemDecoration以及Margin
             不进行measureChildWithMargins（）的话，下面的getDecoratedMeasuredWIdth/Height将测的结果为0，也就是测不出结果
           */
            measureChildWithMargins(scrap, 0, 0);
            int perItemWidth = getDecoratedMeasuredWidth(scrap);
            int perItemHeight = getDecoratedMeasuredHeight(scrap);
            /*
            Log.i(TAGG,"测量的每个item的宽度和高度： perItemWidth  perIntemHeight = "+perItemWidth +"  "+perItemHeight );
            打印结果perItemWidth=1080    dp为单位算出的是：perIntemHeight=150  px为单位算出的是perIntemHeight=50
            */
            //layoutDecorated作用：将ViewLayout出来，显示在屏幕上，内部会自动追加上该View的ItemDecoration和Margin。此时我们的View已经可见了
            layoutDecorated(scrap, 0, offsetY, perItemWidth, offsetY + perItemHeight);
            offsetY += perItemHeight;
        }

        mTotalHeight = offsetY;
        Log.i(TAGG, "最后计算得到的 总高度offsetY = " + mTotalHeight);
        /*
        输出结果 mTotalHeight = 3150  为150 * 21 21为总共的数据量
        */

    }

    /*
    canScrollVertically()：自定义管理器的时候，视图能够支持滑动必须重载这个方法并且设为true，
                           否则recycleview将无法进行滑动
    scrollVerticallyBy():  这两个方法必须都要重载，这样recycleview才能滑动
     */
    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int theRvVisibleHeight = getVerticalVisibleHeight();
        int theMoreHeight = mTotalHeight - theRvVisibleHeight;
        if (mTheMoveDistance + dy < 0) { //抵达上边界

            offsetChildrenVertical(0);
        } else if (mTotalHeight > theRvVisibleHeight && mTheMoveDistance + dy > theMoreHeight) {//抵达下边界
            offsetChildrenVertical(0);
        } else {
            offsetChildrenVertical(dy);//调用父类的方法
        }


        mTheMoveDistance += dy;

        return dy * (-1);
        // return super.scrollVerticallyBy(dy, recycler, state);
    }



    @Override
    public void offsetChildrenVertical(int dy) {
        super.offsetChildrenVertical(-1 * dy);
    }



    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    public int getVerticalVisibleHeight() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }


    /*
     *这个方法是为了算出一个item的高度，也就是平均高度，有了这个高度我们就可以算出一个屏幕可以显示多少个item了
     *这个Adam为首个条目（意为亚当），有了这个均高，我们才能算出可见屏幕能容纳多少条item。
    private int initStepHeight(RecyclerView.Recycler recycler) {
        View Adam = recycler.getViewForPosition(0);
        addView(Adam);
        int result = -1;
        measureChildWithMargins(Adam, 0, 0);
        result = getDecoratedMeasuredHeight(Adam);
        removeAndRecycleAllViews(recycler);
        return result;
    }
    */

    /*
     * 初始化可见条目数，addview以及layout

    private int initVisibleCounts(int stepHeight) {
        int verticalVisibleHeight = getVerticalVisibleHeight();
        return verticalVisibleHeight / stepHeight;
    }
     */

    /**
     * 算出可见范围
     * @return

    private Rect getVisibleArea() {
        Rect result = new Rect(getPaddingLeft(), getPaddingTop() + mTheMoveDistance, getWidth() + getPaddingRight(), getVerticalVisibleHeight() + mTheMoveDistance);
        return result;
    }
     */
    /**
     *

    初始化显示的条数
    private void layoutChildren(RecyclerView.Recycler recycler, int visibleCount) {

        detachAndScrapAttachedViews(recycler);

        for (int i = 0; i < visibleCount; i++) {
            View viewForPositionChild = recycler.getViewForPosition(i);
            measureChildWithMargins(viewForPositionChild, 0, 0);
            addView(viewForPositionChild);

            layoutDecorated(viewForPositionChild, itemRects.get(i).left, itemRects.get(i).top, itemRects.get(i).right, itemRects.get(i).bottom);
        }

    }
     */
}

