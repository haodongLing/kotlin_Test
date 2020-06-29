package com.haodong.practice.kotlin.github.app.utils

import com.google.gson.Gson

/**
 * created by linghaoDo on 2020/6/29
 * description:
 *
 * version:
 */
inline fun <reified T> Gson.fromJson(json:String)=fromJson(json,T::class.java)