package com.example.a11699.all.videoplay.bean

/**
 *Create time 2020/5/9
 *Create Yu
 */
class VideoBean {
    var videoPath: String? = null
    var videoImg: Int? = null

    constructor(videoPath: String, videoImg: Int) {
        this.videoPath = videoPath
        this.videoImg = videoImg
    }
}