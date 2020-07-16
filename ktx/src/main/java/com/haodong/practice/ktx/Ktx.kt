package com.haodong.practice.ktx

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.haodong.practice.ktx.core.lifecycle.KtxLifeCycleCallBack

/**
 * created by linghaoDo on 2020/7/16
 * description:
 *
 * version:
 */
class Ktx : ContentProvider() {
    companion object {
        lateinit var app: Application
        var watchActivityLife = true
        var watchAppLife = true
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun query(p0: Uri, p1: Array<out String>?, p2: String?, p3: Array<out String>?, p4: String?): Cursor? {
        TODO("Not yet implemented")
    }

    override fun onCreate(): Boolean {
        val application = context!!.applicationContext as Application
        install(application)
        return true
    }

    private fun install(application: Application) {
        app = application
        if (watchActivityLife) application.registerActivityLifecycleCallbacks(KtxLifeCycleCallBack())
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }
}