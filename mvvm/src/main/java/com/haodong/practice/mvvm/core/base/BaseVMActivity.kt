package com.haodong.practice.mvvm.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * created by linghaoDo on 2020/6/23
 * description:
 *
 * version:
 */
abstract class BaseVMActivity : AppCompatActivity() {
    protected inline fun <reified T : ViewDataBinding> binding(@LayoutRes resId: Int) : Lazy<T> = lazy {
        DataBindingUtil.setContentView<T>(this, resId).apply {
            lifecycleOwner = this@BaseVMActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    open fun getLayoutId(): Int = 0
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}