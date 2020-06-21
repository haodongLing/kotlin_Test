package com.haodong.kotlintest.coroutines.python

import java.lang.IllegalStateException
import java.lang.Thread.yield
import kotlin.coroutines.*

/**
 * created by linghaoDo on 2020/6/17
 * description:
 *
 * version:
 */
interface Generator<T> {
    operator fun iterator(): Iterator<T>

}

abstract class GeneratorScope<T> internal constructor() {
    protected abstract val parameter: T
    abstract suspend fun yield(value: T)
}

sealed class State {
    class NotReady(val continuation: Continuation<Unit>) : State()
    class Ready<T>(val continuation: Continuation<Unit>, val nextValue: T) : State()
    object Done : State()
}


class GeneratorItrator<T>(private val block: suspend GeneratorScope<T>.(T) -> Unit, override val parameter: T) :
    GeneratorScope<T>(), Iterator<T>, Continuation<Any?> {
    override val context: CoroutineContext = EmptyCoroutineContext
    private var state: State

    init {
        /*将有参数的lambda表达式变成无参表达式*/
        val corutineBlock: suspend GeneratorScope<T>.() -> Unit = { block(parameter) }
        val start = corutineBlock.createCoroutine(this, this)
        state = State.NotReady(start)
    }

    override suspend fun yield(value: T) = suspendCoroutine<Unit> { continuation ->
        state = when (state) {
            is State.NotReady -> State.Ready(continuation, value)
            is State.Ready<*> -> throw IllegalStateException("can not yield a value while ready")
            State.Done -> throw IllegalStateException("can not yield a value while done")
        }
    }

    private fun resume() {
        when (val currentState = state) {
            is State.NotReady -> currentState.continuation.resume(Unit)
            is State.Ready<*> -> true
            State.Done -> false
        }
    }

    override fun hasNext(): Boolean {
        resume()
        return state != State.Done
    }

    override fun next(): T {
        return when(val currentState=state){
            is State.NotReady -> {
                resume()
                return next()
            }
            is State.Ready<*> -> {
                state = State.NotReady(currentState.continuation)
                (currentState as State.Ready<T>).nextValue
            }
            State.Done -> throw IndexOutOfBoundsException("No value left.")
        }
    }


    override fun resumeWith(result: Result<Any?>) {
        state=State.Done
        result.getOrThrow()
    }

}

class GeneratorImpl<T>(private val block: suspend GeneratorScope<T>.(T) -> Unit, private val parameter: T) : Generator<T> {
    override fun iterator(): Iterator<T> {
        return GeneratorItrator(block,parameter)
    }
}

/**
 * 返回gennerator的实例
 */
fun <T> generator(block: suspend GeneratorScope<T>.(T) -> Unit): (T) -> Generator<T> {
    return { parameter: T ->
        GeneratorImpl(block, parameter)
    }
}
fun main() {
    val nums = generator { start: Int ->
        for (i in 0..5) {
            yield(start + i)
        }
    }

    val seq = nums(10)

    for (j in seq) {
        println(j)
    }

    val sequence = sequence {
        yield(1)
        yield(2)
        yield(3)
        yield(4)
        yieldAll(listOf(1,2,3,4))
    }

    for(xx in sequence){
        println(xx)
    }
}