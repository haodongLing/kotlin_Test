package com.haodong.practice.wanandroid.ui

import android.os.Bundle
import com.haodong.practice.mvvm.core.base.BaseActivity
import com.haodong.practice.wanandroid.R

/**
 * created by linghaoDo on 3/17/21
 * description:
 *
 * version:
 */
class NavigationActivity : BaseActivity() {
    override fun initView() {
    }

    override fun initData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_navigation
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

    }
}