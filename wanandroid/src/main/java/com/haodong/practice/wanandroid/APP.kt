package com.haodong.practice.wanandroid

import android.app.Application
import android.content.Context
import com.haodong.practice.mvvm.core.util.Timer
import com.haodong.practice.wanandroid.di.appModule
import com.haodong.practice.wanandroid.model.bean.User
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.properties.Delegates

/**
 * created by linghaoDo on 3/12/21
 * description:
 *
 * version:
 */
class App : Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
        lateinit var CURRENT_USER: User
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Timer.start(APP_START)
    }
    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }

        //x5内核初始化接口
//        QbSdk.initX5Environment(applicationContext, object : QbSdk.PreInitCallback {
//
//            override fun onViewInitFinished(arg0: Boolean) {
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                Log.d("app", " onViewInitFinished is $arg0")
//            }
//
//            override fun onCoreInitFinished() {
//            }
//        })
    }
}