package com.haodong.practice.wanandroid.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.haodong.practice.mvvm.core.base.BaseFragment
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.ui.main.MainFragment
import com.haodong.practice.wanandroid.ui.profile.ProfileFragment
import com.haodong.practice.wanandroid.ui.project.BlogFragment
import com.haodong.practice.wanandroid.ui.project.ProjectFragment
import com.haodong.practice.wanandroid.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

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
    init {
        fragmentList.run {
            add(mainFragment)
            add(blogFragment)
            add(searchFragment)
            add(projectFragment)
            add(profileFragment)
        }
    }

    override fun getLayoutResId(): Int {
        return  R.layout.activity_bottom_navigation
    }

    override fun initView() {
        initViewPager()
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelected)
    }
    private val onNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.home -> {
                switchFragment(0)
            }
            R.id.blog -> {
                switchFragment(1)
            }
            R.id.search -> {
                switchFragment(2)
            }
            R.id.project -> {
                switchFragment(3)
            }
            R.id.profile -> {
                switchFragment(4)
            }
        }
        true
    }
    private fun switchFragment(position: Int): Boolean {
//        mainViewpager.currentItem = position
        mainViewpager.setCurrentItem(position, false)
        return true
    }
    private fun initViewPager() {
        mainViewpager.isUserInputEnabled = false
        mainViewpager.offscreenPageLimit = 2
        mainViewpager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int) = fragmentList[position]

            override fun getItemCount() = fragmentList.size
        }
    }


    override fun initData() {
    }
}