package com.example.a11699.all.dialogment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.a11699.all.R;

/**
 * 作者：余智强
 * 2019/2/26
 */
public abstract class BaseDialog extends DialogFragment {
    @LayoutRes //表明该参数、变量或者函数返回值应该是一个 layout 布局文件类型的资源
    protected int mLayoutResId;

    private float mDimAmount = 0.5f;//背景昏暗度
    private boolean mShowbottomEnable;//是否底部展示
    private int mMargin = 0;//左右边距
    private int mAnimStyle = 0;//进入退出动画
    private boolean mOutCancel = true;//点击外部取消
    private Context mcontext;
    private int mWidth;
    private int mHeight;
    private String TAG = "zjc";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
        Log.i(TAG, "onAttach调用了");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate被调用了");
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
        mLayoutResId = setUpLayoutId();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("zjc","onCreateView被调用了");
        View view = inflater.inflate(mLayoutResId, container, false);
        convertView(ViewHolder.create(view), this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("zjc","onStart被调用了");
        initParams();
    }

    private void initParams() {
        Log.i("zjc","initParams被调用了");

        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = mDimAmount;

            //设置dialog显示位置
            if (mShowbottomEnable) {
                params.gravity = Gravity.BOTTOM;
            }

            //设置dialog宽度
            if (mWidth == 0) {
                params.width = getScreenWidth(getContext()) - 2 * dp2px(getContext(), mMargin);
            } else {
                params.width = dp2px(getContext(), mWidth);
            }

            //设置dialog高度
            if (mHeight == 0) {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                params.height = dp2px(getContext(), mHeight);
            }

            //设置dialog动画
            if (mAnimStyle != 0) {
                window.setWindowAnimations(mAnimStyle);
            }

            window.setAttributes(params);
        }
        setCancelable(mOutCancel);
    }

    /**
     * 设置dialog布局
     */
    public abstract int setUpLayoutId();

    /**
     * 操作dialog布局
     */
    public abstract void convertView(ViewHolder holder, BaseDialog dialog);

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 设置背景昏暗度
     *
     * @param dimAmount
     * @return
     */
    public BaseDialog setDimAmout(@FloatRange(from = 0, to = 1) float dimAmount) {
        mDimAmount = dimAmount;
        return this;
    }

    /**
     * 设置宽高
     *
     * @param width
     * @param height
     * @return
     */
    public BaseDialog setSize(int width, int height) {
        mWidth = width;
        mHeight = height;
        return this;
    }

    /**
     * 设置左右margin
     *
     * @param margin
     * @return
     */
    public BaseDialog setMargin(int margin) {
        mMargin = margin;
        return this;
    }

    /**
     * 设置进入退出动画
     *
     * @param animStyle
     * @return
     */
    public BaseDialog setAnimStyle(@StyleRes int animStyle) {
        mAnimStyle = animStyle;
        return this;
    }

    /**
     * 设置是否点击外部取消
     *
     * @param outCancel
     * @return
     */
    public BaseDialog setOutCancel(boolean outCancel) {
        mOutCancel = outCancel;
        return this;
    }

    public BaseDialog show(FragmentManager manager) {
        Log.i("zjc","show被调用了");
        super.show(manager, String.valueOf(System.currentTimeMillis()));
        return this;
    }


}
