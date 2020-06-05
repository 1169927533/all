package com.example.a11699.all.voiceband


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment

import android.widget.MediaController
import com.example.a11699.all.R

import kotlinx.android.synthetic.main.activity_voice_band.*


import java.io.File

import android.util.Log


class VoiceBandActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_band)
        initView()

    }

    private fun initView() {
        /**
         * 本地视频播放
         */
        var path = initVideoPath()

        videoView.setVideoPath(path)//传一个本地路径
        /**
         * 网络视频播放
         */
        //  videoView.setVideoURI(Uri.parse("http://test.static.orzzhibo.com/20200310/69db180ca2f0c18df33f910fa0c9d38a.mp4"))//需要转换uri
        /**
         * 播放停止和暂停 可以通过MediaController 控制视频放
         */
        var mediaController = MediaController(this)
        /**
         * 设置Mediaplayer和VideoVideo的关联
         */
        videoView.setMediaController(mediaController)
        /**
         * 建立MediaController和VideoView的关联
         */
        mediaController.setMediaPlayer(videoView)
    }


    private fun initVideoPath(): String {
        //判断sd卡是否存在
        var sdCardExit = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
        if(sdCardExit){
            var sdDir = Environment.getExternalStorageDirectory()
            var sdPath = sdDir.absolutePath
        }


        val filePath = Environment.getExternalStorageDirectory().path + "/myvideo.mp4"
        val file = File(filePath)
        if (file.exists()) {
            Log.i("Zjc", "wenjain cun zaei")
        } else {
            Log.i("Zjc", "文件不存在")
        }
        return file.getPath()
    }

}
