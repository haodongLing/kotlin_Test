package com.haodong.practice.wanandroid.ui.login

import android.app.ProgressDialog
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.haodong.practice.ktx.ext.toast
import com.haodong.practice.mvvm.core.base.BaseVMFragment
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.title_layout.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * created by linghaoDo on 3/16/21
 * description:
 *
 * version:
 */
class LoginFragment : BaseVMFragment<LoginViewModel>() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun initVM(): LoginViewModel {
        return getViewModel()
    }

    override fun initView() {
        (mBinding as ActivityLoginBinding).viewModel = mViewModel
        mToolbar.setTitle(R.string.login)
        mToolbar.setNavigationIcon(R.drawable.arrow_back)
    }

    override fun initData() {
        mToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(viewLifecycleOwner, Observer {
                if (it.isLoading) showProgressDialog()
                it.isSuccess?.let {
                    dismissDialog()
                    findNavController().navigateUp()
                }
                it.isError?.let { err ->
                    dismissDialog()
                    activity?.toast(err)
                }
            })
        }
    }

    private var progressDialog: ProgressDialog? = null
    private fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
            progressDialog?.show()
        }

    }

    private fun dismissDialog() {
        progressDialog?.dismiss()
    }
}