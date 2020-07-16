package com.haodong.practice.ktx.core.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.haodong.practice.ktx.ext.logv

/**
 * created by linghaoDo on 2020/7/16
 * description:
 *
 * version:
 */
class KtxAppLifeObserver : LifecycleObserver {
    fun onForeground() {
        "应用进入前台".logv()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground() {
        "应用进入后台".logv()
    }
}