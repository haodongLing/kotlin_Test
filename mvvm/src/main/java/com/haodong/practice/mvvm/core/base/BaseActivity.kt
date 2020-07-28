package com.haodong.practice.mvvm.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * created by linghaoDo on 2020/6/23
 * description:
 *
 * version:
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun getLayoutId(): Int
}