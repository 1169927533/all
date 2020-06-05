package com.example.a11699.all.videoplay.adapter

import android.content.Context
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.a11699.all.R

import com.example.a11699.all.videoplay.bean.VideoBean


/**
 *Create time 2020/5/9
 *Create Yu
 */
class VideoAdapter(context: Context, videoList: ArrayList<VideoBean>) : RecyclerView.Adapter<VideoAdapter.MyVideoViewHolder>() {
    var context: Context? = null
    var videList: ArrayList<VideoBean>? = null

    init {
        this.context = context
        this.videList = videoList
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VideoAdapter.MyVideoViewHolder {
        return MyVideoViewHolder(LayoutInflater.from(context).inflate(R.layout.myvideoitem, p0, false))
    }

    override fun getItemCount(): Int {
        return videList!!.size
    }

    override fun onBindViewHolder(p0: VideoAdapter.MyVideoViewHolder, p1: Int) {
      /*  videList?.let {
            it[p1].videoImg?.let { it1 -> p0.imgVideoBg.setImageResource(it1) }
        }*/
        p0.imgcontrol.setImageResource(R.mipmap.ic_play_circle_outline_white_48dp)
        p0.imgcontrol.setOnClickListener {
            p0.imgcontrol.visibility = View.GONE
            p0.imgVideoBg.visibility = View.GONE
            p0.fraContentHost?.let { it1 -> clickVideoItem?.invoke(p1, p0) }
        }

        loadCover(p0.imgVideoBg, videList?.get(p1)!!.videoPath, context)
    }

    /**
     * 加载第四秒的帧数作为封面
     * url就是视频的地址
     */
    fun loadCover(imageView: ImageView, url: String?, context: Context?) {
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(context)
                .setDefaultRequestOptions(
                        RequestOptions()
                                .frame(4000000)
                                .centerCrop()
                                .error(R.mipmap.bg) //可以忽略
                                .placeholder(R.mipmap.bg) //可以忽略
                )
                .load(url)
                .into(imageView)
    }


    class MyVideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var imgVideoBg = itemView.findViewById<ImageView>(R.id.imgVideoBg)
        public var imgcontrol = itemView.findViewById<ImageView>(R.id.imgcontrol)
        public var fraContentHost: FrameLayout? = itemView.findViewById<FrameLayout>(R.id.fraContentHost);
    }

    var clickVideoItem: ((clickPosition: Int, p0: VideoAdapter.MyVideoViewHolder) -> Unit)? = null
}