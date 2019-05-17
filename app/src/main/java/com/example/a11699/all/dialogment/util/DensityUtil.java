package com.example.a11699.all.dialogment.util;

import android.content.Context;

/**
 * 作者：余智强
 * 2019/2/26
 */
public class DensityUtil {
    /**
     * dp转px
     * @param dip       dp
     * @param context   上下文
     * @return
     */
    public static int dip2px(float dip, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);// 4.9->4, 4.1->4, 四舍五入
        return px;
    }

    /**
     * px转dp
     * @param px        px
     * @param context   上下文
     * @return
     */
    public static float px2dip(int px, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        float dp = px / density;
        return dp;
    }
}
