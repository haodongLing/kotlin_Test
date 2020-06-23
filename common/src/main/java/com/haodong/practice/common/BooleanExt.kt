package com.haodong.practice.common

/**
 * created by linghaoDo on 2020/6/23
 * description:
 *
 * version:
 */
sealed class BooleanExt<out T>
object Otherwise : BooleanExt<Nothing>()
class WithData<T>(val data: T) : BooleanExt<T>()

inline fun <T> Boolean.yes(block: () -> T) =
    when {
        this -> {
            WithData(block())
        }
        else -> {
            Otherwise
        }
    }


inline fun <T> Boolean.no(block: () -> T) = when {
    this -> {
        Otherwise
    }
    else -> {
        WithData(block())
    }
}

inline fun <T> BooleanExt<T>.otherWise(block: () -> T): T = when (this) {
    is Otherwise -> block();
    is WithData -> this.data

}
