package com.example.a11699.all.myviewpage.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.AdapterView;

/**
 * Create time 2020/2/20
 * Create Yu
 */
public class CMLoopViewPager extends ViewPager {
    OnPageChangeListener mOuterPageChangeListener;
    private AdapterView.OnItemClickListener onItemClickListener;
    public CMLoopViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public CMLoopViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        super.addOnPageChangeListener(onPageChangeListener);
    }

    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
