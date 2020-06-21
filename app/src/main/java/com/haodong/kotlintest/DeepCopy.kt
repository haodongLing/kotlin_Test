package com.haodong.kotlintest

import kotlin.reflect.full.primaryConstructor

/**
 * created by linghaoDo on 2020-05-29
 * description:
 *
 * version:
 */
data class Person3(val id: Int, val name: String, val group: Group)

data class Group(val id: Int, val name: String, val location: String)

fun main() {
    val person3 = Person3(0, "name1", Group(0, "Kotliner.cn", "China"))

    val copiedPerson3 = person3.copy();
    val deepCopyedPerson3 = person3
}
