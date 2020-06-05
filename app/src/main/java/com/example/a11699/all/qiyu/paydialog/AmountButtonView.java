package com.example.a11699.all.qiyu.paydialog;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a11699.all.R;
import com.example.a11699.all.qiyu.paydialog.bean.GoldBean;

/**
 * Create time 2020/3/24
 * Create Yu
 */
public class AmountButtonView {
    View view;
    private Context context;
    ImageView image_glod, img_isSelect;
    TextView tv_goldmoney,tv_goldnum;
    ConstraintLayout btn_gold;
    boolean isShow = false;

    GoldBean goldBean;

    public AmountButtonView(Context context, boolean isShow, GoldBean goldBean) {
        this.isShow = isShow;
        this.goldBean = goldBean;
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.bottom_pricebtn, null);
        image_glod = view.findViewById(R.id.image_glod);
        img_isSelect = view.findViewById(R.id.img_isSelect);
        btn_gold = view.findViewById(R.id.btn_gold);
        tv_goldmoney = view.findViewById(R.id.tv_goldmoney);
        tv_goldnum = view.findViewById(R.id.tv_goldnum);
        if (isShow) {
            image_glod.setVisibility(View.VISIBLE);
            img_isSelect.setVisibility(View.VISIBLE);
        }
        btn_gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick();
                if (btn_gold.isSelected()) {
                    img_isSelect.setVisibility(View.GONE);
                    btn_gold.setSelected(false);
                } else {
                    img_isSelect.setVisibility(View.VISIBLE);
                    btn_gold.setSelected(true);
                }
            }
        });
        if (goldBean != null) {
            tv_goldmoney.setText(goldBean.getGoudMoney()+"");
            tv_goldnum.setText(goldBean.getGoldNum()+"");
        }

    }

    public View getView() {
        return view;
    }

    public interface OnClick {
        void onItemClick();
    }

    OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }
}
