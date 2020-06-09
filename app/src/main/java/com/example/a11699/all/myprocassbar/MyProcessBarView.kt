package com.example.a11699.all.myprocassbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.a11699.all.R


/**
 *Create time 2020/6/5
 *Create Yu
 */
class MyProcessBarView : View {
    var cp_percent_textsize = 18//文字大小
    var cp_percent_textcolor = 0xff009ACD//文字颜色
    var cp_background_color = 0xff636363//进度条背景色
    var cp_progress_color = 0xff00C5CD
    var cp_background_is_stroke = true
    var cp_rect_round = 5
    var mPaint: Paint
    var processCurrent = 0
    var processMax = 100
    private var mCenterX = 0
    private var mCenterY = 0

    var progressCurrent = 0
    var progressMax = 100


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        var typedArray = context.obtainStyledAttributes(R.styleable.CustomProcessView);
        cp_percent_textsize = typedArray.getDimension(R.styleable.CustomProcessView_cp_percent_textsize, cp_percent_textsize.toFloat()).toInt();
        cp_percent_textcolor = typedArray.getColor(R.styleable.CustomProcessView_cp_percent_textcolor, cp_percent_textcolor.toInt()).toLong()
        cp_background_color = typedArray.getColor(R.styleable.CustomProcessView_cp_background_color, cp_background_color.toInt()).toLong()
        cp_progress_color = typedArray.getColor(R.styleable.CustomProcessView_cp_progress_color, cp_progress_color.toInt()).toLong()
        cp_background_is_stroke = typedArray.getBoolean(R.styleable.CustomProcessView_cp_background_is_stroke, cp_background_is_stroke);
        cp_rect_round = typedArray.getDimension(R.styleable.CustomProcessView_cp_rect_round, cp_rect_round.toFloat()).toInt()
        typedArray.recycle();
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG);
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mCenterX = (width / 2).toInt()
        mCenterY = (height / 2).toInt()
        drawHorProgress(mPaint,canvas)
    }
    fun drawHorProgress(paint:Paint,canvas: Canvas?){
        //画背景
        paint.color = cp_background_color.toInt()
        if (cp_background_is_stroke) {
            paint.style = Paint.Style.STROKE
            paint.setStrokeWidth(1F)
        } else {
            paint.style = Paint.Style.FILL
        }

        canvas!!.drawRoundRect(RectF((mCenterX - width / 2).toFloat(), (mCenterY - height / 2).toFloat(),
                (mCenterX + width / 2).toFloat(), (mCenterY + height / 2).toFloat()), cp_rect_round.toFloat(), cp_rect_round.toFloat(), paint)

        //画进度条
        paint.color = cp_progress_color.toInt()
        paint.style = Paint.Style.FILL

        canvas!!.drawRoundRect(RectF((mCenterX - width / 2).toFloat(), (mCenterY - height / 2).toFloat(),
                ((progressCurrent * width / progressMax) as Int).toFloat(), (mCenterY + height / 2).toFloat()), cp_rect_round.toFloat(), cp_rect_round.toFloat(), paint)

        //画文字
        paint.color = cp_percent_textcolor.toInt()
        paint.textSize = cp_percent_textsize.toFloat()
        paint.style = Paint.Style.FILL
        val value_str: String = ((progressCurrent * 100 / progressMax)).toString()
        val rect = Rect()
        paint.getTextBounds(value_str, 0, value_str.length, rect)

        var textWidth: Float = rect.width().toFloat()
        val textHeight: Float = rect.height().toFloat()
        if (textWidth >= width) {
            textWidth = width.toFloat()
        }
        val metrics = paint.fontMetrics
        val baseline = (measuredHeight - metrics.bottom + metrics.top) / 2 - metrics.top
        canvas!!.drawText(value_str, mCenterX - textWidth / 2, baseline, paint)

    }
  /*  fun getProgressCurrent(): Int {
        return progressCurrent
    }*/

    fun setProgressCurrentt(progressCurrent: Int) {
        if (progressCurrent > progressMax) {
            this.progressCurrent = progressMax
        } else {
            this.progressCurrent = progressCurrent
        }
        postInvalidate()
    }
}