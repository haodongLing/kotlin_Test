package com.haodong.kotlintest.coroutines.lua

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * created by linghaoDo on 2020/6/18
 * description:
 *
 * version:
 */
sealed class Status {
    class Created(val continuation: Continuation<Unit>) : Status()
    class Yielded<P>(val continuation: Continuation<P>) : Status()
    class Resumed<R>(val continuation: Continuation<R>) : Status()
    object Dead : Status()
}
//class Coroutine <P,R>(
//    val context:CoroutineContext=EmptyCoroutineContext,
//    private val block:suspend Coroutine<P,R>.Ci=i)
//
//
//suspend fun main() {
//    val producer = Coroutine.create<Unit, Int>(Dispatcher()) {
//        for (i in 0..3) {
//            log("send", i)
//            yield(i)
//        }
//        200
//    }
//
//    val consumer = Coroutine.create<Int, Unit>(Dispatcher()) { param: Int ->
//        log("start", param)
//        for (i in 0..3) {
//            val value = yield(Unit)
//            log("receive", value)
//        }
//    }
//
//    while (producer.isActive && consumer.isActive) {
//        val result = producer.resume(Unit)
//        consumer.resume(result)
//    }
//}