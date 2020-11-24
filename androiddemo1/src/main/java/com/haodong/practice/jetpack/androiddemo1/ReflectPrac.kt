package com.haodong.practice.jetpack.androiddemo1

import com.haodong.practice.jetpack.androiddemo1.data.User

/**
 * created by linghaoDo on 2020/11/10
 * description:
 *
 * version:
 */
interface RefApi {
    fun getUsers(): List<User>
}

fun main() {
    fun contravariant(){
//        val dustbin:Dustbin<Waste>=d
    }
}

open class Waste
open class DryWaste : Waste()
open class Dustbin<in T : Waste> {
    private var list = ArrayList<T>()
    fun put(t: T) {
        list.add(t);
    }

}


