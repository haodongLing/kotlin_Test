package com.haodong.practice.kotlin.github.app.utils

import com.haodong.practice.common.sp.Preference
import com.haodong.practice.kotlin.github.app.AppContext
import kotlin.reflect.jvm.jvmName

/**
 * Created by benny on 6/23/17.
 */
inline fun <reified R, T> R.pref(default: T) = Preference(AppContext, "", default, R::class.jvmName)

