package com.haodong.practice.mvvm.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * created by linghaoDo on 2020/6/23
 * description:
 *
 * version:
 */
abstract class BaseVMActivity<VM : BaseViewModel>(useDataBinding: Boolean = true) : AppCompatActivity() {
    private val _useBinding = useDataBinding;
    protected lateinit var mBinding: ViewDataBinding
    lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = initVM()
        startObserve()
        if (_useBinding) {
            mBinding = DataBindingUtil.setContentView(this, getLayoutId())
            mBinding.lifecycleOwner = this

        } else setContentView(getLayoutId())
        initView()
        initData()
    }

    open fun getLayoutId(): Int = 0
    abstract fun initVM(): VM
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}