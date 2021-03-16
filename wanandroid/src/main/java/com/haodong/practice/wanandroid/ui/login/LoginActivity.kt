package com.haodong.practice.wanandroid.ui.login

import androidx.activity.viewModels
import com.haodong.practice.mvvm.core.base.BaseVMActivity
import com.haodong.practice.wanandroid.BR
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.model.bean.Title
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * created by linghaoDo on 3/16/21
 * description:
 *
 * version:
 */
//@AndroidEntryPoint
class LoginActivity : BaseVMActivity<LoginViewModel>() {
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initVM(): LoginViewModel {
        return getViewModel()
    }

    override fun initView() {
        mBinding.run {
            setVariable(BR.viewModel, loginViewModel)
            setVariable(BR.title, Title(R.string.login, R.drawable.arrow_back) { onBackPressed() })
        }
    }

    override fun initData() {
    }

    override fun startObserve() {
        loginViewModel.apply { }
    }
}