package com.haodong.kotlintest

/**
 * created by linghaoDo on 2020-05-06
 * description:
 *
 * version:
 */
operator fun String.minus(right: Any?) = this.replaceFirst(right.toString(), "")

operator fun String.times(right: Int): String {
    return (1..right).joinToString("") { this }
}

operator fun String.div(right: Any?): Int {
    val right = right.toString()
    return this.windowed(right.length, 1, transform = {
        it == right
    }).count() { it }
}

data class Student(
    val name: String,
    val age: Int,
    val sex: String,
    val score: Int,
    val hobbies: List<String>
)

fun main() {
    val jillen = Student("Jilen", 30, "m", 85, listOf("coding", "reed"))
    val show = Student("show", 30, "m", 85, listOf("coding", "reed"))
    val yison = Student("yison", 30, "m", 85, listOf("coding", "reed"))
    val jack = Student("jack", 30, "m", 85, listOf("coding", "reed"))
    val students = listOf(jillen, show, yison, jack)
    students.map { it.hobbies }.flatten();
}