package com.haodong.practice.jetpack.androiddemo1.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor

/**
 * created by linghaoDo on 2020/11/20
 * description:
 *
 * version:
 */
class LogContinuationInterceptor : ContinuationInterceptor {
    //    companion object Key=LogContinuationIntercepter()
    override val key = ContinuationInterceptor
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return LogContinuation(continuation)
    }

}

class LogContinuation<T>(private val continuation: Continuation<T>) : Continuation<T> by continuation {
    override fun resumeWith(result: Result<T>) {
        println("before resumeWith:$result")
        continuation.resumeWith(result)
        println("after resumeWith ")

    }

}