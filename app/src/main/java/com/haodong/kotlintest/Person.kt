package com.haodong.kotlintest

/**
 * created by linghaoDo on 2020-05-06
 * description:
 *
 * version:
 */
class Person(val age: Int, val name: String) {
    override fun equals(other: Any?): Boolean {
        val other = (other as? Person) ?: return false
        return other.age == age && other.name == name
    }

    override fun hashCode(): Int {
        return 1 + 7 * age + 13 * name.hashCode()
    }
}

fun main() {
    val persons = HashSet<Person>();
    val person = Person(20, "Benny")
    persons += person
    println(persons.size)
}