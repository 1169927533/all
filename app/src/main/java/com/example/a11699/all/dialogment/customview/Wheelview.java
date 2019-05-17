package com.example.a11699.all.dialogment.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DebugUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.a11699.all.R;
import com.example.a11699.all.dialogment.ItemAdapter;
import com.example.a11699.all.dialogment.util.DensityUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Boolean.TRUE;

/**
 * 作者：余智强
 * 2019/2/26
 */
public class Wheelview extends RelativeLayout {
    Calendar selectCalendar;
    int time;
    float parentWidth, parentHeight;
    private List<String> stringList;
    private int initPosition, currentPosition;
    float oneHeight, oneWidth;
    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    ItemAdapter itemAdapter;
    int wheelType;
    int isUpOrDown;

    public Wheelview(Context context, Calendar calendar, int time, float win_width, float win_height, List<String> yearList, int position) {
        super(context);
        this.selectCalendar = calendar;
        this.time = time;
        this.parentWidth = win_width;
        this.parentHeight = win_height;
        this.stringList = yearList;
        this.initPosition = position;
    }

    public interface OnCurrentItemListener {
        void onCurrent(int a, String b);
    }

    OnCurrentItemListener onCurrentItemListener;

    public void setOnCurrentItemListener(OnCurrentItemListener onCurrentItemListener) {
        this.onCurrentItemListener = onCurrentItemListener;
    }

    void setWheelEnable(Boolean wheelEnable) {

    }

    void updateData(List<String> list, int currentPosition) {
        this.stringList = list;
        this.currentPosition = currentPosition;
    }

    /**
     * 初始化内部控件
     */
    private void init() {
        currentPosition = initPosition;
        // 初始化滚动器的宽高
        oneHeight = (parentHeight - DensityUtil.dip2px(4, getContext())) / 3;
        oneWidth = parentWidth / 5;
        final int padding = DensityUtil.dip2px(3, getContext());
        LayoutParams params = new LayoutParams((int) oneWidth, (int) parentHeight);
        setLayoutParams(params);
        this.setPadding(padding, 0, padding, 0);
        // 设置内部控件左右两边的空隙
        mRecyclerView = new RecyclerView(getContext());
        mRecyclerView.setOverScrollMode(OVER_SCROLL_NEVER);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        // 指定显示三行数据
        layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                if (getChildCount() > 0) {
                    View firstChildView = recycler.getViewForPosition(0);
                    measureChild(firstChildView, widthSpec, heightSpec);
                    setMeasuredDimension(MeasureSpec.getSize(widthSpec), firstChildView.getMeasuredHeight() * 3 - DensityUtil.dip2px(4, getContext()));
                } else {
                    super.onMeasure(recycler, state, widthSpec, heightSpec);
                }
            }
        };
        mRecyclerView.setLayoutManager(layoutManager);
        // 添加数据
        itemAdapter = new ItemAdapter();
        mRecyclerView.setAdapter(itemAdapter);

        // 滑动监听获取当前的item
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            RecyclerView.LayoutManager layoutManager;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // now State  0静止,没有滚动  1 正在被外部拖拽,一般为用户正在用手指滚动  2 自动滚动开始
                if (newState == 0) {
                    layoutManager = recyclerView.getLayoutManager();
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == currentPosition) {
                        // TODO
                    } else if (onCurrentItemListener != null) {
                        int nowPosition = (linearLayoutManager.findLastCompletelyVisibleItemPosition());
                        String text = stringList.get(nowPosition % stringList.size())
                                .replace("年", "")
                                .replace("月", "")
                                .replace("日", "")
                                .replace("时", "")
                                .replace("分", "");
                        // 判断是否是过去的日期
                        if (wheelType == Calendar.MONTH) {
                            selectCalendar.set(wheelType, Integer.valueOf(text) - 1);
                        } else {
                            selectCalendar.set(wheelType, Integer.valueOf(text));
                        }
                        // 判断是否是小于当前时间
                        if (selectCalendar.getTimeInMillis() >= System.currentTimeMillis()) {
                            onCurrentItemListener.onCurrent(nowPosition % stringList.size(), stringList.get(nowPosition % stringList.size()));
                            layoutManager.scrollToPosition(nowPosition + 1);
                            itemAdapter.notifyItemChanged(nowPosition, 0);
                            itemAdapter.notifyItemChanged(currentPosition, 1);
                            currentPosition = nowPosition;
                        } else {
                            // 判断是向上还是线下 isUpOrDown>向下，isUpOrDown<0向上
                            if (isUpOrDown > 0) {
                                currentPosition = initPosition;
                                // 由于滚动有些偏差，使用scrollToPositionWithOffset 来滚动
                                linearLayoutManager.scrollToPositionWithOffset(currentPosition - 1, -DensityUtil.dip2px(3, getContext()));
                                itemAdapter.notifyItemChanged(currentPosition, 0);
                            } else {
                                currentPosition = initPosition;
                                linearLayoutManager.scrollToPositionWithOffset(currentPosition - 1, -DensityUtil.dip2px(3, getContext()));
                                itemAdapter.notifyItemChanged(currentPosition, 0);
                            }
                        }
                    }
                } else {
                    itemAdapter.notifyItemChanged(currentPosition, 1);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isUpOrDown = dy;
            }
        });


        // 添加滚动器的的双杠线
        View topLineView = new View(getContext());
        View bottomLineView = new View(getContext());
        topLineView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        bottomLineView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

        LayoutParams topLayoutParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(1, getContext()));
        topLayoutParam.setMargins(0, (int) oneHeight, 0, 0);
        topLayoutParam.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);

        LayoutParams bottomLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(1, getContext()));
        bottomLayoutParams.setMargins(0, (int) (oneHeight * 2), 0, 0);
        bottomLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        // 滑动到初始位置 -1是为了移动到中间位置
        layoutManager.scrollToPosition(stringList.size() * 1000 + initPosition - 1);
        currentPosition = stringList.size() * 1000 + initPosition;
        // 添加所有初始化后的空间
        addView(mRecyclerView, layoutParams);
        addView(topLineView, topLayoutParam);
        addView(bottomLineView, bottomLayoutParams);
    }
}
