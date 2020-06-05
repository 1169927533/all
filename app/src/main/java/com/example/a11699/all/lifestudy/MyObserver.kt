package com.example.a11699.all.lifestudy

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent

/**
 *Create time 2020/4/22
 *Create Yu
 */
class MyObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun dissConnect() {

    }
}