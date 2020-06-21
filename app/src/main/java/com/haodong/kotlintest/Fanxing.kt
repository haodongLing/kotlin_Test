package com.haodong.kotlintest

/**
 * created by linghaoDo on 2020-05-15
 * description:
 *
 * version:
 */
interface MyBook {

}

interface EduBook : MyBook {

}

class BookStore<out T : MyBook> {
    fun getBook(): T {
        TODO()
    }
}


fun main() {

}