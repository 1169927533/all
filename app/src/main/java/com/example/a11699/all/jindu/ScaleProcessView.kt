package com.example.a11699.all.jindu

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.a11699.all.R
import com.example.a11699.all.dialogment.BaseDialog.dp2px
import java.text.DecimalFormat

/**
 *Create time 2020/2/27
 *Create Yu
 */
class ScaleProcessView : View {
    //边框颜色
    private var sideColor: Int = 0
    //文字颜色
    private var textColor: Int = 0
    //边框粗细
    private var sideWidth: Float = 0.toFloat()
    //边框所在的矩形
    private var sidePaint: Paint? = null
    private var srcPaint:Paint?  = null
    private var nearOverText: String? = null
    private var overText: String? = null
    private var textSize: Float = 0.toFloat()
    private var isNeedAnim: Boolean = false

    private var widthw: Int = 0;
    private var heighth: Int = 0;
    private var radious: Float = 0.0f;//圆角半径

    private var processCount: Int = 0;
    private var currentCount = 0
    private var totalCount = 0
    private lateinit var bgRectF: RectF //外边框

    private lateinit var bgSrc:Bitmap
    private lateinit var bgBitmap: Bitmap;
    private lateinit var fgSrc:Bitmap

    private var scale = 0.0f

    private lateinit var mPorterDuffXfermode:PorterDuffXfermode

    constructor(context: Context) : super(context) {
        ScaleProcessView(context, null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initAttrs(context, attributeSet)
        initPaint()
    }

    fun initAttrs(context: Context, attributeSet: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.SaleProgressView)
        sideColor = ta.getColor(R.styleable.SaleProgressView_sideColor, -0xc3ce)
        textColor = ta.getColor(R.styleable.SaleProgressView_textColor, -0xc3ce)
        sideWidth = ta.getDimension(R.styleable.SaleProgressView_sideWidth, dp2px(2f).toFloat())
        overText = ta.getString(R.styleable.SaleProgressView_overText)
        nearOverText = ta.getString(R.styleable.SaleProgressView_nearOverText)
        textSize = ta.getDimension(R.styleable.SaleProgressView_textSize, sp2px(16f).toFloat())
        isNeedAnim = ta.getBoolean(R.styleable.SaleProgressView_isNeedAnim, true)
        ta.recycle()
    }

    fun initPaint() {
        //画笔基本步骤 1.设置画笔样式 是填充型的 还是画边型的
        //2.设置画笔粗细
        //3.设置画笔颜色
        sidePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        sidePaint!!.style = Paint.Style.STROKE;//绘制边框
        sidePaint!!.strokeWidth = sideWidth;
        sidePaint!!.color = sideColor


        srcPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPorterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //获取view的宽高
        widthw = measuredWidth
        heighth = measuredHeight

        radious = heighth / 2.0f
        bgRectF = RectF(sideWidth, sideWidth, widthw - sideWidth, heighth - sideWidth)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!isNeedAnim) {
            processCount = currentCount
        }
        if (totalCount == 0) {
            scale = 0.0f
        } else {
            //算的就是一个占比
            scale = java.lang.Float.parseFloat(DecimalFormat("0.00").format((processCount.toFloat() / totalCount.toFloat()).toDouble()))
        }
        drawSide(canvas)
        drawBg(canvas)//绘制背景
        drawFg(canvas)//绘制进度条

        //这里是为了演示动画方便，实际开发中进度只会增加
        if (processCount != currentCount) {
            if (processCount < currentCount) {
                processCount++
            } else {
                processCount--
            }
            postInvalidate()
        }
    }

    fun drawSide(canvas: Canvas?) {
        canvas?.drawRoundRect(bgRectF, radious, radious, sidePaint)
    }

    fun drawBg(canvas: Canvas?) {
        bgBitmap = Bitmap.createBitmap(widthw,heighth,Bitmap.Config.ARGB_8888)
        var bgCanves = Canvas(bgBitmap)//上面两步骤是创建一个画布

        bgSrc = BitmapFactory.decodeResource(resources,R.mipmap.bg)
        bgCanves.drawRoundRect(bgRectF,radious,radious,srcPaint)

        srcPaint!!.setXfermode(mPorterDuffXfermode)
        bgCanves.drawBitmap(bgSrc,null,bgRectF,srcPaint)

        canvas?.drawBitmap(bgBitmap,0f,0f,null)
        srcPaint?.setXfermode(null)
    }

    fun drawFg(canvas: Canvas?){
        if(scale == 0.0f){
            return
        }
        var fgBitmap = Bitmap.createBitmap(widthw,heighth,Bitmap.Config.ARGB_8888)
        var fgCanvas = Canvas(fgBitmap)
        fgSrc = BitmapFactory.decodeResource(resources,R.mipmap.fg)
        fgCanvas.drawRoundRect(
                RectF(sideWidth, sideWidth, (width - sideWidth) * scale, height - sideWidth),
                radious, radious, srcPaint) //目标 这里绘制的是一个动态变长的效果
        srcPaint?.setXfermode(mPorterDuffXfermode)
        fgCanvas.drawBitmap(fgSrc,null,bgRectF,srcPaint)//原  然后通过叠加实现 红色的逐渐变长

        canvas?.drawBitmap(fgBitmap,0f,0f,null)
        srcPaint?.setXfermode(null)
    }

    fun setTotalAndCurrentCount(totalCount: Int, currentCount: Int) {
        var currentCount = currentCount
        this.totalCount = totalCount
        if (currentCount > totalCount) {
            currentCount = totalCount
        }
        this.currentCount = currentCount
        postInvalidate()
    }
    private fun dp2px(dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun sp2px(spValue: Float): Int {
        val scale = context.resources.displayMetrics.scaledDensity
        return (spValue * scale + 0.5f).toInt()
    }
}