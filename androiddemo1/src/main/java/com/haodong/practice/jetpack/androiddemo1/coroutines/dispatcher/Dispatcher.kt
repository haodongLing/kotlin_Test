package com.haodong.practice.jetpack.androiddemo1.coroutines.dispatcher

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

/**
 * created by linghaoDo on 2020/11/24
 * description:
 *
 * version:
 */
interface Dispatcher {
    fun dispatch(block: () -> Unit)

}

open class DispatcherContext(private val dispatcher: Dispatcher) :
    AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
        DispatchedContinuation(continuation, dispatcher, continuation.context)


}

private class DispatchedContinuation<T>(
    val delegate: Continuation<T>, val dispatcher: Dispatcher,
    override val context: CoroutineContext
) : Continuation<T> {
    override fun resumeWith(result: Result<T>) {
        dispatcher.dispatch {
            delegate.resumeWith(result)
        }

    }

}