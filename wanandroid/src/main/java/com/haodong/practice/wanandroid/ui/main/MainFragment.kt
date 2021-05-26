package com.haodong.practice.wanandroid.ui.main

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.haodong.practice.mvvm.core.base.BaseFragment
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.ui.home.HomeFragment
import com.haodong.practice.wanandroid.ui.navigation.NavigationFragment
import com.haodong.practice.wanandroid.ui.project.ProjectTypeFragment
import com.haodong.practice.wanandroid.ui.square.SquareFragment
import com.haodong.practice.wanandroid.ui.system.SystemFragment
import com.haodong.practice.wanandroid.util.Preference
import kotlinx.android.synthetic.main.activity_new_main.*

/**
 * created by tangyuan on 2021/5/14
 * description:
 *
 */
class MainFragment : BaseFragment() {
    private var isLogin by Preference(Preference.IS_LOGIN, true)
    private val titleList = arrayOf("首页", "广场", "最新项目", "体系", "导航")
    private val fragmentList = arrayListOf<Fragment>()
    private val homeFragment by lazy { HomeFragment() }
    private val squareFragment by lazy { SquareFragment() }
    private val lastedProjectFragment by lazy { ProjectTypeFragment.newInstance(0,true) }
    private val systemFragment by lazy { SystemFragment() } // 体系
    private val navigationFragment by lazy { NavigationFragment() }
    private var mOnPageChangeCallback: ViewPager2.OnPageChangeCallback? = null


    override fun getLayoutResId(): Int {
        return R.layout.activity_new_main
    }
    init {
        fragmentList.add(homeFragment)
        fragmentList.add(squareFragment)
        fragmentList.add(lastedProjectFragment)
        fragmentList.add(systemFragment)
        fragmentList.add(navigationFragment)
    }
    fun initViewPager(){
        viewPager.offscreenPageLimit=1
        viewPager.adapter=object : FragmentStateAdapter(this){
            override fun getItemCount(): Int {
              return  titleList.size
            }

            override fun createFragment(position: Int): Fragment {
               return fragmentList[position]
            }


        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titleList[position]
        }.attach()
    }
    override fun onResume() {
        super.onResume()
        if (mOnPageChangeCallback == null) mOnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 1) addFab.show() else addFab.hide()
            }
        }
        mOnPageChangeCallback?.let { viewPager.registerOnPageChangeCallback(it) }
    }

    override fun onStop() {
        super.onStop()
        mOnPageChangeCallback?.let { viewPager.unregisterOnPageChangeCallback(it) }
    }


    override fun initView() {
        initViewPager()
        addFab.setOnClickListener(View.OnClickListener{
            if (!isLogin) Navigation.findNavController(viewPager).navigate(R.id.action_tab_to_login)
            else Navigation.findNavController(viewPager).navigate(R.id.action_tab_to_share)
        })
    }

    override fun initData() {

    }
}