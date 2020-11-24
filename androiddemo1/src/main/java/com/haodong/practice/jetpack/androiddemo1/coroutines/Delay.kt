package com.haodong.practice.jetpack.androiddemo1.coroutines

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume

/**
 * created by linghaoDo on 2020/11/23
 * description:
 *
 * version:
 */
private val executor = Executors.newScheduledThreadPool(1) {

        it ->
    Thread(it, "Delay-Scheduler").apply { isDaemon = true }

}
@InternalCoroutinesApi
suspend fun delay(time:Long, timeUnit: TimeUnit)= suspendCancellableCoroutine<Unit> {
    continuation->
    val future=executor.schedule({continuation.resume(Unit)},time,timeUnit)
    continuation.invokeOnCancellation {
        future.cancel(true)
    }
}