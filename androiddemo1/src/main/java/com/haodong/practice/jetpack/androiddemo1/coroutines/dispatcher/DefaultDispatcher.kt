package com.haodong.practice.jetpack.androiddemo1.coroutines.dispatcher

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

/**
 * created by linghaoDo on 2020/11/24
 * description:
 *
 * version:
 */
private val threadGroup = ThreadGroup("defaultDispatcher")
private val threadIndex = AtomicInteger(0)
private val executor = Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors()) {
    Thread(threadGroup, it, "${threadGroup.name}-worker-${threadIndex.getAndIncrement()}").also { it ->
        it.isDaemon = true
    }
}

object DefaultDispatcher : Dispatcher {
    override fun dispatch(block: () -> Unit) {
        executor.submit(block)
    }

}