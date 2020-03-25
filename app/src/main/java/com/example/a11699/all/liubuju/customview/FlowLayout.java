package com.example.a11699.all.liubuju.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

// 流式标签布局
public class FlowLayout extends ViewGroup {
    int realWidth = 0; //父容器的宽度
    int oneChildTop = dpToPx(26);//每一个子view距离顶部的高度
    List oneLineWidthList = new ArrayList();//记录每一行所有子view包括每个子view的margin全部加起来的宽度
    private Context mContext;

    /**
     * 适用于当New出一个对象的时候会调用一个参数的构造方法
     *
     * @param context
     */
    public FlowLayout(Context context) {
        this(context, null);
    }

    /**
     * 在布局文件中调用属性但是没有调用自定义属性时会调用两个参数的构造方法
     *
     * @param context
     * @param attrs
     */
    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 在布局文件中当使用自定义属性的时候会调用三个参数的构造方法
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            //子View的宽度和高度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin + oneChildTop;
            //换行
            if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
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
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

        /*    // 这个判断可以实现 自动换行 当到达最大值自动换行
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - getPaddingRight() - getPaddingLeft()) {

            }*/

            //这个判断实现 一行最多显示三个
            if (i % 3 == 0 || childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - getPaddingRight() - getPaddingLeft()) {
                mLineHeight.add(lineHeight);
                //记录当前行的Views
                mAllViews.add(lineViews);
                //重置宽和高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin + oneChildTop;
                //重置行View集合
                lineViews = new ArrayList<>();
            }


            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin + oneChildTop);
            lineViews.add(child);
        }

        //处理最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        //设置子View的位置
        int left = getPaddingLeft();
        int top = getPaddingTop();
        //行数
        int lineNum = mAllViews.size();

        int linWidth = 0;
        for (int i = 0; i < lineNum; i++) {
            //当前行的所有View
            lineViews = mAllViews.get(i);
            linWidth = 0;
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                linWidth += (lp.leftMargin + child.getMeasuredWidth() + lp.rightMargin);
            }
            oneLineWidthList.add(linWidth);
        }


        for (int ii = 0; ii < lineNum; ii++) {
            //当前行的所有View
            lineViews = mAllViews.get(ii);
            lineHeight = mLineHeight.get(ii);
            int shengWidth = realWidth - (int) oneLineWidthList.get(ii); //除了绘制子所剩余的距离
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);

                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = shengWidth / 2 + left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                lineWidth += (lp.leftMargin + child.getMeasuredWidth() + lp.rightMargin);
                //对子View 进行布局
                child.layout(lc, tc + oneChildTop, rc, bc + oneChildTop);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            //换行
            left = getPaddingLeft();
            top += lineHeight;
        }
    }

    int dpToPx(int dps) {
        return Math.round(getResources().getDisplayMetrics().density * dps);
    }


}

