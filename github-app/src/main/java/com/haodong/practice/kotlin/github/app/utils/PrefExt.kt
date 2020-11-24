package com.haodong.practice.kotlin.github.app.utils

import com.haodong.practice.common.sp.Preference
import com.haodong.practice.kotlin.github.app.App
import kotlin.reflect.jvm.jvmName

/**
 * Created by benny on 6/23/17.
 */
inline fun <reified R, T> R.pref(default: T) = Preference(App.AppContext, "", default, R::class.jvmName)

