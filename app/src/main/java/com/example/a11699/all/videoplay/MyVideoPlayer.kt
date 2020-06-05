package com.example.a11699.all.videoplay

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.FrameLayout
import com.example.a11699.all.R
import com.example.a11699.all.videoplay.adapter.VideoAdapter
import com.example.a11699.all.videoplay.bean.VideoBean
import com.example.a11699.all.videoplay.customview.MyVideoView
import kotlinx.android.synthetic.main.activity_my_video_player.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class MyVideoPlayer : AppCompatActivity() {
    private val imageIds = intArrayOf(R.drawable.hzw_a, R.drawable.hzw_b,
            R.drawable.hzw_d, R.drawable.hzw_e, R.drawable.hzw_f, R.drawable.hzw_h,
            R.drawable.hzw_i, R.drawable.hzw_j, R.drawable.hzw_k)
    private val videoFile = arrayOf("myvideo2", "myvideo3", "myvvv", "myvideo2", "myvideo3", "myvvv", "myvideo2", "myvideo3", "myvvv")
    private val VIDEO_PATH = "https://dn-chunyu.qbox.me/fwb/static/images/home/video/video_aboutCY_A.mp4"
    var listVideo = ArrayList<VideoBean>()
    var myVideoView: MyVideoView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_video_player)

        /* var imageView = ImageView(this)
         imageView.setImageResource(R.drawable.card_view_image)
         var layoutParams = RelativeLayout.LayoutParams(120, 120)
         layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
         imageView.layoutParams = layoutParams
         customrelayout.addChildView(imageView)*/

        for (filename in videoFile) {
            listVideo.add(VideoBean(initData(filename), R.drawable.hzw_a))
        }
        var videoAdapter = VideoAdapter(this, listVideo)
        video_recycleview.layoutManager = LinearLayoutManager(this)
        video_recycleview.adapter = videoAdapter
        videoAdapter.clickVideoItem = ::clickItem
        //通过加载XML动画设置文件来创建一个Animation对象；
        var animation = AnimationUtils.loadAnimation(this, R.anim.slide_right);   //得到一个LayoutAnimationController对象；
        var controller = LayoutAnimationController(animation);   //设置控件显示的顺序；
        controller.order = LayoutAnimationController.ORDER_NORMAL
        controller.delay = (0.3f)
        video_recycleview.layoutAnimation = controller
        video_recycleview.startLayoutAnimation();

    }

    var lastView: VideoAdapter.MyVideoViewHolder? = null
    private fun clickItem(position: Int, videoAdapter: VideoAdapter.MyVideoViewHolder) {
        if (lastView != null) {
            lastView!!.imgVideoBg.visibility = View.VISIBLE
            lastView!!.imgcontrol.visibility = View.VISIBLE
            lastView!!.fraContentHost!!.removeAllViews()
        }
        if (myVideoView == null) {
            myVideoView = MyVideoView(this)
        }
        myVideoView?.stop()

        var layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        myVideoView?.layoutParams = layoutParams
        videoAdapter.fraContentHost?.addView(myVideoView)
        myVideoView?.setVideoPath(listVideo[position].videoPath)
        myVideoView?.start()
        lastView = videoAdapter

    }

    fun initData(filName: String): String {

        val filePath = Environment.getExternalStorageDirectory().path + "/DCIM/Camera/$filName.mp4"

        val file = File(filePath)
        if (file.exists()) {
            Log.i("Zjc", "wenjain cun zaei")
        } else {
            Log.i("Zjc", "文件不存在")
        }
        return filePath
    }

}
