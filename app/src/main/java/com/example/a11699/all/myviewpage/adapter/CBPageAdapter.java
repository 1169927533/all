package com.example.a11699.all.myviewpage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.example.a11699.all.myviewpage.holder.CBViewHolderCreator;
import com.example.a11699.all.myviewpage.view.CMLoopViewPager;

import java.util.List;

/**
 * Create time 2020/2/20
 * Create Yu
 */
public class CBPageAdapter<T> extends PagerAdapter {
    protected List<T> mDatas;
    protected CBViewHolderCreator holderCreator;
    private boolean canLoop = true;
    private CMLoopViewPager viewPager;
    private final int MULTIPLE_COUNT = 300;

    public int toRealPosition(int position) {
        int realCOunt = getRealCount();
        if (realCOunt == 0) {
            return 0;
        }
        int realPosition = position % realCOunt;
        return realPosition;
    }

    private int getRealCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public int getCount() {
        return canLoop ? getRealCount() * MULTIPLE_COUNT : getRealCount();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = toRealPosition(position);

        View view =new View(null);// = getView(realPosition, null, container);
//        if(onItemClickListener != null) view.setOnClickListener(onItemClickListener);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return false;
    }

    class ViewScroller extends Scroller{
        public ViewScroller(Context context) {
            super(context);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy);
        }

    }


}
