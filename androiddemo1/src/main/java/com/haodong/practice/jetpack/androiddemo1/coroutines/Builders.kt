package com.haodong.practice.jetpack.androiddemo1.coroutines

import android.os.Build
import androidx.annotation.RequiresApi
import com.haodong.practice.jetpack.androiddemo1.coroutines.core.StandardCoroutine
import com.haodong.practice.jetpack.androiddemo1.coroutines.dispatcher.Dispatchers
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

/**
 * created by linghaoDo on 2020/11/23
 * description:
 *
 * version:
 */
@RequiresApi(Build.VERSION_CODES.N)
fun launch(
    context: CoroutineContext = EmptyCoroutineContext, block: suspend () -> Unit
): Job {
    val completion = StandardCoroutine(newCoroutineContext(context))
    block.startCoroutine(completion)
    return completion
}

//private var coroutine
private var coroutineIndex = AtomicInteger(0)
fun newCoroutineContext(context: CoroutineContext): CoroutineContext {
    val combined = context + context + CoroutineName("@coroutine#${coroutineIndex.getAndIncrement()}")
    return if (combined !== Dispatchers.Default && combined[ContinuationInterceptor] == null)
        combined + Dispatchers.Default else combined

}

