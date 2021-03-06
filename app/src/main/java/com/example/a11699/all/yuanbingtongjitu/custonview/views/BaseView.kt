package com.example.a11699.all.yuanbingtongjitu.custonview.views

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * author: ClericYi
 * time: 2020/3/24
 */
open class BaseView : View {


    constructor(context: Context) : super(context) {
        initAnimation()
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initAnimation()
    }

    protected var mWidth = 0f
    protected var mHeight = 0f

    protected var scale = 0.5f

    protected var animator: ValueAnimator? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        mHeight = MeasureSpec.getSize(heightMeasureSpec).toFloat()
    }

    private fun initAnimation() {
        animator = ValueAnimator.ofFloat(0.2f, 1f)
        // 通过插值器来完成动画
        animator?.interpolator = LinearInterpolator()
        animator?.duration = 1000
        animator?.addUpdateListener { animation ->
            scale = animation.animatedValue as Float
            Log.e("BaseView", scale.toString())
            postInvalidate()
        }
    }

}