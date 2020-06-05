package com.example.a11699.all.videoplay.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

/**
 *Create time 2020/5/10
 *Create Yu
 */
class CustomRelayout : RelativeLayout {
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyInt: Int) : super(context, attributeSet, defStyInt) {
        initView()
    }

    private fun initView() {

    }

    public fun addChildView(view: View) {
        addView(view)
    }
}