package com.haodong.practice.common

import android.content.ContentValues.TAG
import android.util.Log
import java.io.File
import java.lang.Exception

/**
 * created by linghaoDo on 2020/6/29
 * description:
 *
 * version:
 */
private const val TAG = "FileExt"
fun File.ensureDir(): Boolean {
    try {
        isDirectory.no {
            isFile.yes { delete() }
            return mkdir()
        }
    }catch (e:Exception){
        Log.w(TAG, e.message)
    }
    return false
}