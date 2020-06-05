package com.example.a11699.all.qiyu.paydialog.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Create time 2020/3/10
 * Create Yu
 */
// 流式标签布局
public class PayFlowLayout extends ViewGroup {
    int realWidth = 0; //父容器的宽度
    int oneChildTop = dpToPx(26);//每一个子view距离顶部的高度
    List oneLineWidthList = new ArrayList();//记录每一行所有子view包括每个子view的margin全部加起来的宽度
    int childWidth_real = 0;
    private Context mContext;

    public PayFlowLayout(Context context) {
        this(context, null);
    }

    public PayFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public PayFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 测量模式和测量值在其中
     *
     * @param widthMeasureSpec  父类穿来的宽度的测量值
     * @param heightMeasureSpec 父类穿来的高度的测量值
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //------------传入的精确值match_parent
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);

        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //-------------------wrap_content

        int width = 0;
        int height = 0;
        //记录每一行的宽度和高度
        int lineWidth = 0;
        int lineHeight = 0;
        //得到内部元素的个数
        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {

            View child = getChildAt(i);
            //测量子View的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //得到LayoutParams（子View的LayoutParams其实是父布局的LayouParams）
            ViewGroup.MarginLayoutParams marginParams = getMarginLayoutParams(child);

            //子View的宽度和高度
            int childWidth = child.getMeasuredWidth() + marginParams.leftMargin + marginParams.rightMargin;
            int childHeight = child.getMeasuredHeight() + marginParams.topMargin + marginParams.bottomMargin;
            childWidth_real = childWidth;
            //换行
            if (i % 3 == 0) {
                //当前行的宽度和已经记录的最大行的宽度进行对比留下最大宽度
                //布局的宽度width是最宽行的宽度
                width = Math.max(width, lineWidth);
                //重置行宽
                lineWidth = childWidth;

                //累加行高
                height += lineHeight + oneChildTop;

                lineHeight = childHeight;
                //不换行
            } else {
                //叠加行宽
                lineWidth += childWidth;
                //以最宽的子View的高度为当前的行高
                lineHeight = Math.max(lineHeight, childHeight);
            }

            //最后一个控件特殊处理(最后一行的高度没有累加，宽度没有比较)
            //如果换行执行if就没有处理换行后的布局的宽度和高度
            //如果不换行执行else就没有叠加当前行的高度和比较最大的行宽
            if (i == cCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }

        //这个是父容器的宽度
        realWidth = modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight();
        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()
        );
    }

    /**
     * 与当前ViewGroup对应的layoutparams
     *
     * @param attributeSet
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);//这里只关注MarginLayoutParams
    }

//    @Override为什么上面的方法不提示？只有下面的方法可选择
//    protected LayoutParams generateLayoutParams(LayoutParams p) {
//        return super.generateLayoutParams(p);
//    }

    /**
     * 以行来存储所有的View
     */
    private List<List<View>> mAllViews = new ArrayList<>();

    /**
     * 每行的高度
     */
    private List<Integer> mLineHeight = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //会多次调用onLayout，所以先clear
        mAllViews.clear();
        mLineHeight.clear();
        //当前ViewGroup的宽度
        int width = getWidth();//此时已经执行了onMeasure（），可以直接调用getWidth获得宽度
        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<>();
        int cCount = getChildCount();


        for (int i = 0; i < cCount; i++) {

            View child = getChildAt(i);

            ViewGroup.MarginLayoutParams marginParams = getMarginLayoutParams(child);
            int childHeight = child.getMeasuredHeight();
            //这个判断实现 一行最多显示三个
            if (i % 3 == 0) {
                mLineHeight.add(lineHeight);
                //记录当前行的Views
                mAllViews.add(lineViews);
                //重置宽和高
                lineHeight = childHeight + marginParams.topMargin + marginParams.bottomMargin + oneChildTop;
                //重置行View集合
                lineViews = new ArrayList<>();
            }
            lineHeight = Math.max(lineHeight, childHeight + marginParams.topMargin + marginParams.bottomMargin + oneChildTop);
            lineViews.add(child);
        }

        //处理最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        //设置子View的位置
        int left = 0;
        int top = getPaddingTop();
        //行数
        int lineNum = mAllViews.size();

        for (int ii = 0; ii < lineNum; ii++) {
            //当前行的所有View
            lineViews = mAllViews.get(ii);
            lineHeight = mLineHeight.get(ii);
            int shengWidth = (realWidth - childWidth_real * 3) / 4; //除了绘制子所剩余的距离
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                int lc = shengWidth + left;
                int tc = top;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();
                child.layout(lc, tc + oneChildTop, rc, bc + oneChildTop);
                left += child.getMeasuredWidth() + shengWidth;
            }
            //换行
            left = 0;
            top += lineHeight;
        }
    }

    int dpToPx(int dps) {
        return Math.round(getResources().getDisplayMetrics().density * dps);
    }


    ViewGroup.MarginLayoutParams getMarginLayoutParams(View child) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        //获取view的margin设置参数
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) lp;
        } else {
            //不存在时创建一个新的参数
            //基于View本身原有的布局参数对象
            marginParams = new ViewGroup.MarginLayoutParams(lp);
        }
        marginParams.setMargins(0, 0, 1, 0);

        return marginParams;
    }

}
