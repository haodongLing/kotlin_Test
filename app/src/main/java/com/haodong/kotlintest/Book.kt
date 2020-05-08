package com.haodong.kotlintest

/**
 * created by linghaoDo on 2020-02-25
 * description:
 *
 * version:
 */
class Book {
    fun main(vararg args: String) {
        if (args.size < 3) {
            return showHelp()
        }
        val operators = mapOf(
            "+" to ::plus,
            "-" to ::minus,
            "*" to ::times,
            "/" to ::div
        )
        val op = args[1]
        val opFunc = operators[op] ?: return showHelp()
        try {
            println("input: ${args.joinToString()}")
            println("output: ${opFunc(args[0].toInt(), args[2].toInt())}")
        } catch (e: Exception) {
            println("Invalid Arguments")
        }
        var person1=Person("name1",12)
        var age2=person1::age
        age2.set(13) // 引用
    }

    fun showHelp() {
        println(
            """
            simple Calculator
            input 3*4
            OutPut: 12
        """.trimIndent()
        )

    }

    fun plus(args0: Int, arg1: Int): Int {
        return args0 + arg1
    }

    fun minus(args0: Int, arg1: Int): Int {
        return args0 - arg1
    }

    fun times(args0: Int, arg1: Int): Int {
        return args0 * arg1
    }

    fun div(args0: Int, arg1: Int): Int {
        return args0 / arg1
    }

     class Person(var name: String, var age: Int? = null)

    class Rectangle(val height: Int, val width: Int) {
        val isSqure: Boolean
            get() {
                return height == width
            }

    }


}