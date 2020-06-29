package com.haodong.practice.kotlin.github.app.utils

import android.content.Context
import android.provider.Settings

/**
 * created by linghaoDo on 2020/6/29
 * description:
 *
 * version:
 */
val Context.deviceId: String
    get() = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

