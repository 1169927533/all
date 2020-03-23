package com.example.a11699.all.myviewpage.holder;

import android.content.Context;
import android.view.View;

/**
 * Create time 2020/2/20
 * Create Yu
 */
public interface Holder<T> {
    View createView(Context context);
    void UpdateUI(Context context,int position,T data);
}
