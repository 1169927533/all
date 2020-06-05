package com.example.a11699.all.loukong.cuscomview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.a11699.all.R
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.AbstractSet
import java.util.jar.Attributes

/**
 *Create time 2020/5/12
 *Create Yu
 */
class Curtain : View {
    var mPaint: Paint? = null

    constructor(context: Context) : super(context) {
        mPaint = Paint()
    }

    constructor(context: Context, attributes: AttributeSet) : super(context,  attributes) {
        mPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBackGround(canvas)
        drawHellowFields(canvas)
    }

    fun drawBackGround(canvas: Canvas?) {
        mPaint?.let { paint ->
            paint.setXfermode(null)
            paint.color = resources.getColor(R.color.color_535559)
            canvas?.drawRect(0F, 0F, width.toFloat(), height.toFloat(), mPaint)
        }
    }

    fun drawHellowFields(canvas: Canvas?) {
        mPaint?.setColor(Color.WHITE);
        mPaint?.setXfermode(PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        //测试画一个圆
        canvas?.drawCircle(getWidth().toFloat() / 2, getHeight().toFloat() / 4, 300F, mPaint);
    }
}