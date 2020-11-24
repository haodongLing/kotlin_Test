package com.haodong.practice.jetpack.androiddemo1.coroutines


import com.haodong.practice.jetpack.androiddemo1.coroutines.core.Disposable
import kotlin.coroutines.CoroutineContext

/**
 * created by linghaoDo on 2020/11/23
 * description:
 *
 * version:
 */
typealias OnComplete=()->Unit
typealias CancellationException = java.util.concurrent.CancellationException
typealias OnCancel = () -> Unit


interface Job:CoroutineContext.Element{
    companion object Key:CoroutineContext.Key<Job>

    override val key: CoroutineContext.Key<*>
        get() = Job
    val isActive:Boolean

    val isCompleted: Boolean
    fun invokeOnCancel(onCancel:OnCancel): Disposable
    fun invokeOnCompletion(onComplete:OnComplete):Disposable
    fun cancel()
    fun remove(disposable: Disposable)
    suspend fun join()// 挂起函数，但是不阻塞当前线程

}