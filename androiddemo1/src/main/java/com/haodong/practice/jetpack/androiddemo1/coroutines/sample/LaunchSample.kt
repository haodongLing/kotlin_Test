package com.haodong.practice.jetpack.androiddemo1.coroutines.sample

import android.util.Log
import com.haodong.practice.jetpack.androiddemo1.coroutines.launch
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * created by linghaoDo on 2020/11/24
 * description:
 *
 * version:
 */

const val TAG = "Coroutine"
suspend fun main() {

    launch {
        Log.i(TAG, "1")
        Log.i(TAG, "2")
        val result = hello();
        Log.i(TAG, "result" + result)

    }.join()
}

suspend fun hello() = suspendCoroutine<Int> { continuation ->
    thread {
        Thread.sleep(1000)
        continuation.resume(20)
    }
}
