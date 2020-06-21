package com.haodong.kotlintest

import java.io.File

/**
 * created by linghaoDo on 2020-05-09
 * description:
 *
 * version:
 */
fun main(){
    File("build.gradle").readText()
        .toCharArray()
        .filter { !it.isWhitespace() }
        .groupBy { it }
        .map { it.key to it.value.size }
        .let { println(it) }
}