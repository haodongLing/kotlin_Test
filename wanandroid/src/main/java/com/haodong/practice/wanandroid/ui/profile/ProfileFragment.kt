package com.haodong.practice.wanandroid.ui.profile

import com.haodong.practice.mvvm.core.base.BaseFragment
import com.haodong.practice.wanandroid.util.Preference

/**
 * created by tangyuan on 2021/5/17
 * description:
 *
 */
class ProfileFragment : BaseFragment() {

    private var isLogin by Preference(Preference.IS_LOGIN, false)
    private var userJson by Preference(Preference.USER_GSON, "")
    override fun getLayoutResId(): Int {
        TODO("Not yet implemented")
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }
}