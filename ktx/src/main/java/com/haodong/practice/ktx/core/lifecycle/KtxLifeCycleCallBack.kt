package com.haodong.practice.ktx.core.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.haodong.practice.ktx.ext.loge

/**
 * created by linghaoDo on 2020/7/16
 * description:
 *
 * version:
 */
class KtxLifeCycleCallBack : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {
        KtxManager.pushActivity(activity)
        "onActivityPaused : ${activity.localClassName}".loge()
    }

    override fun onActivityStarted(activity: Activity) {
        "onActivityStarted : ${activity.localClassName}".loge()
    }

    override fun onActivityDestroyed(activity: Activity) {
        "onActivityDestroyed : ${activity.localClassName}".loge()
    }

    override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
        "onActivitySaveInstanceState : ${activity.localClassName}".loge()
    }

    override fun onActivityStopped(activity: Activity) {
        "onActivityStopped : ${activity.localClassName}".loge()
    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        "onActivityCreated : ${activity.localClassName}".loge()
    }

    override fun onActivityResumed(activity: Activity) {
        "onActivityResumed : ${activity.localClassName}".loge()
    }
}