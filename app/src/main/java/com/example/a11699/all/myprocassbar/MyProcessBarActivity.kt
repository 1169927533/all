package com.example.a11699.all.myprocassbar

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_my_process_bar.*

class MyProcessBarActivity : AppCompatActivity() {
    var processCurrent: Int = 0
    lateinit var mRunnable: Runnable
    var mHandler: Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var text = "aA bB fF gG"
        var tv_info = TextView(this)
        tv_info.setTextColor(Color.BLUE)
        tv_info.setBackgroundColor(0x5500ff00)
        tv_info.layoutParams = LinearLayout.LayoutParams(100, 100)
        tv_info.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
        tv_info.text = text
        setContentView(tv_info)
        tv_info.measure(0, 0) //必须手动调用测量方法，否则getMeasuredHeight获取不到值。另外，即使测量过了getHeight也获取不到值


        val mPaint: Paint = tv_info.paint
        val metrics = mPaint.fontMetrics
        //metrics.top: -57.032227    metrics.bottom 14.633789
        Log.i("bqt", "metrics.top: " + metrics.top + " metrics.bottom " + metrics.bottom)
        //metrics.ascent: -50.097656 metrics.descent 13.183594
        Log.i("bqt", "metrics.ascent: " + metrics.ascent + " metrics.descent " + metrics.descent)
        // ascent():-50.097656，descent():13.183594
        Log.i("bqt", "ascent()的值为" + mPaint.ascent().toString() + "，descent()的值为" + mPaint.descent())
        //字符的高度:63.28125，字符的长度:283.0
        Log.i("bqt", "字符的高度为" + (-mPaint.ascent() + mPaint.descent()).toString() + "，字符的长度为" + mPaint.measureText(text))
        //测量的高度为73，测量的宽度为283
        Log.i("bqt", "测量的高度为" + tv_info.measuredHeight + "，测量的宽度为" + tv_info.measuredWidth)
        val mRect = Rect()
        mPaint.getTextBounds(text, 0, text.length, mRect)
        //字符的边界为2，-42，279，12
        Log.i("bqt", "字符的边界为" + mRect.left.toString() + "，" + mRect.top.toString() + "，" + mRect.right.toString() + "，" + mRect.bottom)
        //字符的高度为54，字符的长度为277
        Log.i("bqt", "字符的高度为" + (mRect.bottom - mRect.top).toString() + "，字符的长度为" + (mRect.right - mRect.left))


        //  MyProcessBar()//R.layout.activity_my_process_bar
    }

    //开启自定义进度条
    fun MyProcessBar() {
        cpv_one.progressMax = 100
        cpv_one.cp_background_color = Color.parseColor("#A2CD5A").toLong()
        cpv_one.cp_percent_textcolor = Color.RED.toLong()
        cpv_one.cp_rect_round = 16
        cpv_one.cp_background_is_stroke = false
        cpv_one.cp_percent_textsize = 30
        cpv_one.setOnClickListener {
            if (processCurrent != 0) {
                processCurrent = 0
                return@setOnClickListener
            }
            mRunnable = Runnable {
                processCurrent += 1
                cpv_one.setProgressCurrentt(processCurrent)
                mHandler.postDelayed(mRunnable, 5)
            }
            mHandler.postDelayed(mRunnable, 1)
        }
    }
}
