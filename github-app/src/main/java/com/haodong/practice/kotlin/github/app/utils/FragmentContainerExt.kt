package com.haodong.practice.kotlin.github.app.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * created by linghaoDo on 2020/7/28
 * description:
 *
 * version:
 */
fun AppCompatActivity.showFragment(containerId: Int, clazz: Class<out Fragment>, vararg args: Pair<String, String>) {
    supportFragmentManager.beginTransaction().replace(
        containerId,
        clazz.newInstance().apply { arguments = Bundle().apply { args.forEach { putString(it.first, it.second) } } },clazz
            .name)
}