package com.haodong.practice.wanandroid.ui.login

import android.app.ProgressDialog
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.haodong.practice.ktx.ext.toast
import com.haodong.practice.mvvm.core.base.BaseVMActivity
import com.haodong.practice.wanandroid.BR
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.databinding.ActivityLoginBinding
import com.haodong.practice.wanandroid.model.bean.Title
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * created by linghaoDo on 3/16/21
 * description:
 *
 * version:
 */
//@AndroidEntryPoint
class LoginActivity : BaseVMActivity() {
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    private val mBinding by binding<ActivityLoginBinding>(R.layout.activity_login)


    override fun initView() {
        mBinding.run {
            setVariable(BR.viewModel, loginViewModel)
            setVariable(BR.title, Title(R.string.login, R.drawable.arrow_back) { onBackPressed() })
        }
    }

    override fun initData() {
    }

    override fun startObserve() {
        loginViewModel.apply {
            uiState.observe(this@LoginActivity, Observer { it ->
                if (it.isLoading) {
                    showProgressDialog()

                    it.isSuccess?.let {
                        dismissProgressDialog()
                        finish()
                    }

                    it.isError?.let {
                        dismissProgressDialog()
                        toast(it)
                    }
                    if (it.needLogin) {
                        loginViewModel.loginFlow()
                    }
                }
            })
        }
    }

    private var progressDialog: ProgressDialog? = null
    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

}