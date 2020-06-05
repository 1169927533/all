package com.example.a11699.all.qiyu.getdialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.a11699.all.R;
import com.example.a11699.all.dialogment.util.DensityUtil;

/**
 * Create time 2020/3/26
 * Create Yu
 */
public class GetGoodsDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Transparent);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_getgood, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER; // 紧贴底部
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT; // 宽度持平
        lp.height =WindowManager.LayoutParams.WRAP_CONTENT; // 宽度持平
        lp.windowAnimations = R.style.BottomDialogAnimation;
        window.setAttributes(lp);
    }
}
