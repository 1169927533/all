package com.example.a11699.all.dialogment.customview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 作者：余智强
 * 2019/2/26
 */
public class PickView extends LinearLayout {
    private Context context;
    private Wheelview rvYear, rvMonth, rvDay, rvHour, rvMinute;

    private List<String> yearList = new ArrayList<>();
    private List<String> monthList = new ArrayList<>();
    private List<String> dayList = new ArrayList<>();
    private List<String> hourList = new ArrayList<>();
    private List<String> minuteList = new ArrayList<>();
    private Calendar calendar;
    private int yearPosition, monthPosition, dayPosition, hourPosition, minutePosition;

    public PickView(Context context) {
        super(context);
        this.context = context;
    }

    public PickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public PickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                initView();
                return false;
            }
        });
        handler.sendEmptyMessage(1);
    }


    private void initView() {

        setOrientation(HORIZONTAL);

        // 初始化时间
        initDate();
        // 初始化控件
        rvYear = new Wheelview(context, calendar, Calendar.YEAR, getMeasuredWidth(), getMeasuredHeight(), yearList, yearPosition);
        rvMonth = new Wheelview(context, calendar, Calendar.MONTH, getMeasuredWidth(), getMeasuredHeight(), monthList, monthPosition);
        rvDay = new Wheelview(context, calendar, Calendar.DAY_OF_MONTH, getMeasuredWidth(), getMeasuredHeight(), dayList, dayPosition);
        rvHour = new Wheelview(context, calendar, Calendar.HOUR_OF_DAY, getMeasuredWidth(), getMeasuredHeight(), hourList, hourPosition);
        rvMinute = new Wheelview(context, calendar, Calendar.MINUTE, getMeasuredWidth(), getMeasuredHeight(), minuteList, minutePosition);

        rvYear.setOnCurrentItemListener(new Wheelview.OnCurrentItemListener() {
            @Override
            public void onCurrent(int position, String content) {
                if (onCurrentDateListener != null) {
                    int year = Integer.parseInt(content.replace("年", ""));
                    calendar.set(Calendar.YEAR, year);
                    onCurrentDateListener.onCurrentDate(PickView.this, calendar.getTimeInMillis());
                }
            }
        });

        rvMonth.setOnCurrentItemListener(new Wheelview.OnCurrentItemListener() {
            @Override
            public void onCurrent(int position, String content) {
                if (onCurrentDateListener != null) {
                    // -1 由于系统计算月份是由0开始
                    int month = Integer.valueOf(content.replace("月", "")) - 1;
                    calendar.set(Calendar.MONTH, month);

                    // 更新这个月的天数
                    dayPosition = 0;
                    getDas(calendar.get(Calendar.YEAR), month + 1, calendar.get(Calendar.DAY_OF_MONTH));
                    rvDay.updateData(dayList, dayPosition);

                    onCurrentDateListener.onCurrentDate(PickView.this, calendar.getTimeInMillis());
                }

            }
        });
        rvDay.setOnCurrentItemListener(new Wheelview.OnCurrentItemListener() {
            @Override
            public void onCurrent(int position, String content) {
                if (onCurrentDateListener != null) {
                    int day = Integer.valueOf(content.replace("日", ""));
                    calendar.set(Calendar.DAY_OF_MONTH, day);
                    onCurrentDateListener.onCurrentDate(PickView.this, calendar.getTimeInMillis());
                }

            }
        });
        rvHour.setOnCurrentItemListener(new Wheelview.OnCurrentItemListener() {
            @Override
            public void onCurrent(int position, String content) {
                if (onCurrentDateListener != null) {
                    int hour = Integer.valueOf(content.replace("时", ""));
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    onCurrentDateListener.onCurrentDate(PickView.this, calendar.getTimeInMillis());
                }

            }
        });
        rvMinute.setOnCurrentItemListener(new Wheelview.OnCurrentItemListener() {
            @Override
            public void onCurrent(int position, String content) {
                if (onCurrentDateListener != null) {
                    int minute = Integer.valueOf(content.replace("分", ""));
                    calendar.set(Calendar.MINUTE, minute);
                    onCurrentDateListener.onCurrentDate(PickView.this, calendar.getTimeInMillis());
                }

            }
        });
        // 添加所有初始化后的页面

        addView(rvYear, 0);
        addView(rvMonth, 1);
        addView(rvDay, 2);
        addView(rvHour, 3);
        addView(rvMinute, 4);

        if (!isEnable) {
            rvYear.setWheelEnable(isEnable);
            rvMonth.setWheelEnable(isEnable);
            rvDay.setWheelEnable(isEnable);
            rvHour.setWheelEnable(isEnable);
            rvMinute.setWheelEnable(isEnable);
        }
    }

    /**
     * 设置初始时间
     *
     * @param updateDate
     */
    public void updateDate(long updateDate) {
        if (updateDate != 0) {
            calendar = Calendar.getInstance();
            calendar.setTime(new Date(updateDate));
        }
    }

    /**
     * 获取获取具体年份的天数,如果是初始化 当前的年月日
     * 如果是获取某年某月的天数day 默认为1
     *
     * @param year
     * @param month
     * @return
     */
    public void getDas(int year, int month, int day) {
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.set(Calendar.YEAR, year);
        newCalendar.set(Calendar.MONTH, month - 1);
        newCalendar.set(Calendar.DATE, 1);
        newCalendar.roll(Calendar.DATE, -1);
        int days = newCalendar.get(Calendar.DATE);
        dayList.clear();
        for (int i = 1; i <= days; i++) {
            if (i < 10) {
                dayList.add("0" + i + "日");
            } else {
                dayList.add(i + "日");
            }
            if (i == day) {
                dayPosition = i - 1;
            }
        }
    }

    /**
     * 初始化时间数据
     */
    private void initDate() {

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        // 初始化年数 和当前年
        for (int i = year - 50; i <= (year + 100); i++) {
            if (i == year) {
                yearPosition = yearList.size();
            }
            yearList.add(i + "年");

        }
        // 初始化月份 和当前月
        for (int i = 1; i <= 12; i++) {
            if (i == month) {
                monthPosition = i;
            }
            monthList.add(i + "月");
        }

        // 初始化天数 和当前天
        getDas(year, month + 1, day);

        // 初始化小时数 和当小时
        for (int i = 0; i < 24; i++) {
            if (hour == i) {
                hourPosition = i;
            }
            if (i < 10) {
                hourList.add("0" + i + "时");
            } else {
                hourList.add(i + "时");
            }
        }
        // 初始化分钟数 和当分钟
        for (int i = 0; i < 12; i++) {

            if (minute / 5 == i) {
                minutePosition = i;
                calendar.set(Calendar.MINUTE, i * 5);
            }
            if (i < 2) {
                minuteList.add("0" + i * 5 + "分");
            } else {
                minuteList.add(i * 5 + "分");
            }
        }

    }

    public interface OnCurrentDateListener {
        /**
         * 获取当前的日期
         *
         * @param view
         * @param date
         */
        void onCurrentDate(View view, long date);
    }

    private OnCurrentDateListener onCurrentDateListener;

    public void setOnCurrentDateListener(OnCurrentDateListener onCurrentDateListener) {
        this.onCurrentDateListener = onCurrentDateListener;
    }

    private boolean isEnable = true;

    public void setWheelEnabled(boolean b) {
        this.isEnable = b;
    }
}