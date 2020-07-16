package com.haodong.practice.ktx.core.lifecycle

import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * created by linghaoDo on 2020/7/16
 * description:
 *
 * version:
 */
class KtxHandler(lifecycleOwner: LifecycleOwner, callback: Callback) : Handler(callback), LifecycleObserver {
    private val mLifecycleOwner: LifecycleOwner = lifecycleOwner

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestory() {
        removeCallbacksAndMessages(null)
        mLifecycleOwner.lifecycle.removeObserver(this)
    }
}