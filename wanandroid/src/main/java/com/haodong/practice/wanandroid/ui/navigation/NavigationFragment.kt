package com.haodong.practice.wanandroid.ui.navigation

import com.haodong.practice.mvvm.core.base.BaseVMFragment
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.databinding.FragmentNavigationBinding
import com.haodong.practice.wanandroid.model.bean.Navigation
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * created by tangyuan on 2021/5/17
 * description:
 *
 */
class NavigationFragment : BaseVMFragment<FragmentNavigationBinding>(R.layout.fragment_navigation) {
    private val navigationAdapter by lazy { NavigationAdapter() }
    private val navigationList = mutableListOf<Navigation>()
    private val tabAdapter by lazy { VerticalTabAdapter(navigationList.map { it.name }) }
    override fun initView() {
        binding.adapter = navigationAdapter

    }

    override fun initData() {

    }

    override fun startObserve() {

    }
}