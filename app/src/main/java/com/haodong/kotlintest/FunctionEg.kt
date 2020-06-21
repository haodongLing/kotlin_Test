package com.haodong.kotlintest

/**
 * created by linghaoDo on 2020-06-01
 * description:
 *
 * version:
 */
fun foo(int:Int){
    print(int)
}
fun main(){
    listOf(1,2,3).forEach{foo(it)}
}