package com.haodong.practice.kotlin.github.app

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import androidx.multidex.MultiDexApplication

private lateinit var INSTANCE: Application

class App: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}