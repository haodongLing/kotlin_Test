package com.haodong.kotlintest

import java.lang.reflect.ParameterizedType
import kotlin.reflect.full.declaredFunctions

/**
 * created by linghaoDo on 2020-05-18
 * description:
 *
 * version:
 */
interface Api {
    fun getUsers(): List<User>
}

abstract class SuperType<T> {
    val typeParameter by lazy {
        this::class.supertypes.first().arguments.first().type!!
    }
    val typeParameterJava by lazy {
//        this.javaClass.genericSuperclass.safeAs<ParameterizedType>()!!.actualTypeArguments.first()
    }

}

class SubType : SuperType<String>()

fun main() {
    Api::class.declaredFunctions.first { it.name == "getUsers" }.returnType.arguments.forEach({
        println(it)
    })
    Api::getUsers.returnType.arguments.forEach {
        println(it)
    }

}