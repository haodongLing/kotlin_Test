package com.haodong.practice.wanandroid.ui

import androidx.fragment.app.Fragment
import com.haodong.practice.mvvm.core.base.BaseFragment
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.ui.main.MainFragment
import com.haodong.practice.wanandroid.ui.profile.ProfileFragment
import com.haodong.practice.wanandroid.ui.project.BlogFragment
import com.haodong.practice.wanandroid.ui.project.ProjectFragment
import com.haodong.practice.wanandroid.ui.search.SearchFragment

/**
 * created by linghaoDo on 3/17/21
 * description:
 *
 * version:
 */
class TabFragment :BaseFragment() {
    private val fragmentList = arrayListOf<Fragment>()
    private val mainFragment by lazy { MainFragment() }
    private val blogFragment by lazy { BlogFragment() }
    private val searchFragment by lazy { SearchFragment() }
    private val projectFragment by lazy { ProjectFragment() }
    private val profileFragment by lazy { ProfileFragment() }

    override fun getLayoutResId(): Int {
        return  R.layout.activity_bottom_navigation
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }
}