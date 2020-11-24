package com.haodong.practice.jetpack.androiddemo1.coroutines.dispatcher

import com.haodong.practice.jetpack.androiddemo1.coroutines.dispatcher.ui.HandlerDispatcher

/**
 * created by linghaoDo on 2020/11/24
 * description:
 *
 * version:
 */
object Dispatchers {
    val Default by lazy {
        DispatcherContext(DefaultDispatcher)
    }
    val Android by lazy {
        DispatcherContext(HandlerDispatcher)
    }
}