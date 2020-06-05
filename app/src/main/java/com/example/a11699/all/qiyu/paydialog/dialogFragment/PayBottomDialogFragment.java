package com.example.a11699.all.qiyu.paydialog.dialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a11699.all.R;
import com.example.a11699.all.qiyu.paydialog.AmountButtonView;
import com.example.a11699.all.qiyu.paydialog.bean.GoldBean;
import com.example.a11699.all.qiyu.paydialog.customview.PayFlowLayout;

/**
 * Create time 2020/3/24
 * Create Yu
 */
public class PayBottomDialogFragment extends DialogFragment {
    Button btn_wpay;//微信支付
    PayFlowLayout flowlayout;//流式布局

    //这里对样式进行设置 全屏还是啥
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.Transparent);
    }

    //在onCreateDialog里设置dialog监听函数，如对返回键的监听。
    //这个最先执行
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pay_dialog, container, false);
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        btn_wpay = view.findViewById(R.id.btn_wpay);
        flowlayout = view.findViewById(R.id.flowlayout);

        btn_wpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmountButtonView amountButtonView;
                if (flowlayout.getChildCount() == 0) {
                    amountButtonView = new AmountButtonView(getContext(), true,new GoldBean(4200,6));
                    flowlayout.addView(amountButtonView.getView());
                } else {
                    amountButtonView = new AmountButtonView(getContext(), false,new GoldBean(21000,30));
                    flowlayout.addView(amountButtonView.getView());
                }

                amountButtonView.setOnClick(new AmountButtonView.OnClick() {
                    @Override
                    public void onItemClick() {
                        changBtnState();
                    }
                });

            }
        });
    }


    void changBtnState() {
        for (int i = 0; i < flowlayout.getChildCount(); i++) {
            View view = flowlayout.getChildAt(i);
            ConstraintLayout button = view.findViewById(R.id.btn_gold);
            button.setSelected(false);
            ImageView img_isSelect = view.findViewById(R.id.img_isSelect);
            img_isSelect.setVisibility(View.GONE);
        }
    }
}
