package com.haodong.practice.jetpack.androiddemo1.coroutines.core

import android.os.Build
import androidx.annotation.RequiresApi
import com.haodong.practice.jetpack.androiddemo1.coroutines.Job
import com.haodong.practice.jetpack.androiddemo1.coroutines.OnComplete
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.CancellationException
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.*

/**
 * created by linghaoDo on 2020/11/23
 * description:
 *
 * version:
 */
@RequiresApi(Build.VERSION_CODES.N)
abstract class AbstractCoroutine<T>(context: CoroutineContext) : Job, Continuation<T> {
    protected val state = AtomicReference<CoroutineState>()
    override val context: CoroutineContext

    init {
        state.set(CoroutineState.InComplete())
        this.context = context + this
    }

    override val isCompleted: Boolean
        get() = state.get() is CoroutineState.Complete<*>
    override val isActive: Boolean
        get() = when (state.get()) {
            is CoroutineState.Complete<*>,
            is CoroutineState.Cancelling
            -> false
            else -> true
        }


    override fun invokeOnCancel(onCancel: OnComplete): Disposable {
        val disposable = CancellationHandlerDisposable(this, onCancel)
        val newState = state.updateAndGet { pre ->
            when (pre) {
                is CoroutineState.InComplete -> {
                    CoroutineState.InComplete().from(pre).with(disposable)
                }
                is CoroutineState.Cancelling,
                is CoroutineState.Complete<*> -> {
                    pre
                }
            }
        }
        (newState as? CoroutineState.Cancelling)?.let {
            onCancel()
        }
        return disposable
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun invokeOnCompletion(onComplete: OnComplete): Disposable {
        return doOnCompleted { block ->
            onComplete()
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun cancel() {
        val newState = state.updateAndGet { prev ->
            when (prev) {
                is CoroutineState.InComplete -> {
                    CoroutineState.Cancelling().from(prev)
                }
                is CoroutineState.Cancelling,
                is CoroutineState.Complete<*> -> {
                    prev
                }
            }
        }
        if (newState is CoroutineState.Cancelling) {
            newState.notifyCancellation()
        }

    }

    override fun remove(disposable: Disposable) {
        state.updateAndGet { prev ->
            when (prev) {
                is CoroutineState.InComplete ->
                    CoroutineState.InComplete().from(prev).without(disposable)
                is CoroutineState.Cancelling -> {
                    CoroutineState.Cancelling().from(prev).without(disposable)
                }
                is CoroutineState.Complete<*> -> {
                    prev
                }
            }

        }
    }

    override suspend fun join() {
        when (state.get()) {
            is CoroutineState.Cancelling,
            is CoroutineState.InComplete -> return joinSuspend()
            /*已经结束*/
            is CoroutineState.Complete<*> -> {
                val currentCallingJobState = coroutineContext[Job]?.isActive ?: return
                if (!currentCallingJobState) {
                    throw CancellationException("Coroutine is cancelled.")
                }
                return
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private suspend fun joinSuspend() = suspendCoroutine<Unit> { continuation ->
        val disposable = doOnCompleted { result ->
            continuation.resume(Unit)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    protected fun doOnCompleted(block: (Result<T>) -> Unit): Disposable {
        val disposable = CompletionHandlerDisposable(this, block)
        val newState = state.updateAndGet { prev ->
            when (prev) {
                is CoroutineState.InComplete -> {
                    CoroutineState.InComplete().from(prev).with(disposable)
                }
                is CoroutineState.Cancelling -> {
                    CoroutineState.Cancelling().from(prev).with(disposable)
                }
                is CoroutineState.Complete<*> -> prev
            }
        }
        (newState as? CoroutineState.Complete<T>)?.let {
            block(
                when {
                    it.value != null -> Result.success(it.value)
                    it.exception != null -> Result.failure(it.exception)
                    else -> throw IllegalStateException("Won't happen!")
                }
            )
        }
        return disposable
    }

    override fun resumeWith(result: Result<T>) {
        val newState = state.updateAndGet { prev ->
            when (prev) {
                is CoroutineState.Cancelling,
                is CoroutineState.InComplete -> {
                    CoroutineState.Complete(result.getOrNull(), result.exceptionOrNull()).from(prev)

                }
                is CoroutineState.Complete<*> -> {
                    throw IllegalStateException("Already completed!")
                }
            }

        }
        newState.notifyCompletion(result)
        newState.clear()
    }

}