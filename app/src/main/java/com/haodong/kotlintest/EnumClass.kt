package com.haodong.kotlintest

/**
 * created by linghaoDo on 2020-05-12
 * description:
 *
 * version:
 */
enum class EnumClass(name: String, age: Int) : Runnable {
    XIAOMING("小明", 12), XIAOWANG("小王", 12);

    override fun run() {
        TODO("Not yet implemented")
    }
}

fun main() {
    println(EnumClass.XIAOMING.name)
}