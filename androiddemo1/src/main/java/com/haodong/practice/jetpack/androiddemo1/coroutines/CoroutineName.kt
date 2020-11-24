package com.haodong.practice.jetpack.androiddemo1.coroutines

import kotlin.coroutines.CoroutineContext

/**
 * created by linghaoDo on 2020/11/20
 * description:
 *
 * version:
 */
class CoroutineName(val name: String) : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<CoroutineName>

    override val key = Key
    override fun toString(): String {
        return name
    }
}