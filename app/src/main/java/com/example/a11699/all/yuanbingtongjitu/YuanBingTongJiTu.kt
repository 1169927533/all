package com.example.a11699.all.yuanbingtongjitu

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.a11699.all.R
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_yuan_bing_tong_ji_tu.*

class YuanBingTongJiTu : AppCompatActivity() {
    private val yellowColor = Color.argb(255, 253, 197, 53)
    private val greenColor = Color.argb(255, 27, 147, 76)
    private val redColor = Color.argb(255, 211, 57, 53)
    private val blueColor = Color.argb(255, 76, 139, 245)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yuan_bing_tong_ji_tu)
        initPieDatas()
    }

    private fun initPieDatas() {
        val mRatios: MutableList<Float> =
                ArrayList()
        val mDescription: MutableList<String> =
                ArrayList()
        val mArcColors: MutableList<Int> = ArrayList()
        mRatios.add(0.4f)
        mRatios.add(0.3f)
        mRatios.add(0.2f)
        mRatios.add(0.1f)
        mArcColors.add(blueColor)
        mArcColors.add(redColor)
        mArcColors.add(yellowColor)
        mArcColors.add(greenColor)
        mDescription.add("描述一")
        mDescription.add("描述二")
        mDescription.add("描述三")
        mDescription.add("描述四")
        //点击动画开启
        sssss.setData(mRatios, mArcColors, mDescription)

    }

}
