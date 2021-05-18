package com.haodong.kotlintest.coroutines.go

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * created by linghaoDo on 2020/6/4
 * description:
 *
 * version:
 */
interface Channel<T> {
    suspend fun send(value: T);
    suspend fun receive(): T
    fun close();

}

class ClosedException(message: String) : Exception(message)

class SimpleChannel<T> : Channel<T> {
    sealed class Element {
        override fun toString(): String {
            return this.javaClass.simpleName
        }

        object None : Element()
        class Producer<T>(val value: T, val continuation: Continuation<Unit>) : Element()
        class Consumer<T>(val continuation: Continuation<T>) : Element();
        object Closed : Element()
    }

    private val state = AtomicReference<Element>(Element.None)


    override fun close() {

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun send(value: T) = suspendCoroutine<Unit> { continuation ->
        val prev = state.getAndUpdate {
            when (it) {
                Element.None -> Element.Producer(value, continuation)
                is Element.Producer<*> -> throw IllegalStateException("Cannot send new element while previous is not consumed.")
                is Element.Consumer<*> -> Element.None
                Element.Closed -> throw IllegalStateException("Cannot send after closed.")
            }
        }
        (prev as? Element.Consumer<T>)?.continuation?.resume(value)?.let { continuation.resume(Unit) }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun receive(): T = suspendCoroutine<T>() {
//        val prev = state.getAndUpdate {
//            when (it) {
//                Element.None -> Element.Consumer(continuation =)
//            }
//        }
    }

}