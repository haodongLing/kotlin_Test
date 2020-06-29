package com.haodong.practice.kotlin.github.app

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
private lateinit var INSTANCE: Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}

object AppContext: ContextWrapper(INSTANCE)