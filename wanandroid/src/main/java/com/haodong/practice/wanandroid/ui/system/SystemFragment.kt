package com.haodong.practice.wanandroid.ui.system

import androidx.databinding.library.baseAdapters.BR
import com.chad.library.adapter.base.BaseQuickAdapter
import com.haodong.practice.ktx.ext.toast
import com.haodong.practice.mvvm.core.base.BaseVMFragment
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.adapter.BaseBindAdapter
import com.haodong.practice.wanandroid.databinding.FragmentSystemBinding
import com.haodong.practice.wanandroid.model.bean.SystemParent
import kotlinx.android.synthetic.main.fragment_system.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * created by tangyuan on 2021/5/17
 * description:
 *
 */
class SystemFragment : BaseVMFragment<FragmentSystemBinding>(R.layout.fragment_system) {
    private val systemViewModel by viewModel<SystemViewModel>()
    private val systemAdapter by lazy { BaseBindAdapter<SystemParent>(R.layout.item_system, BR.systemParent) }

    override fun initView() {
        binding.run {
            adapter = systemAdapter
            viewModel = systemViewModel
        }
        initRecyclerView()


    }

    private fun initRecyclerView() {
        systemAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
//            startKtxActivity<SystemTypeNormalActivity>(value = SystemTypeNormalActivity.ARTICLE_LIST to systemAdapter.data[position])
        }

        systemRefreshLayout.setOnRefreshListener { refresh() }
    }

    private fun refresh() {
        systemViewModel.getSystemTypes()
    }

    override fun initData() {
        refresh()

    }

    override fun startObserve() {
        systemViewModel.run {
            uiState.observe(viewLifecycleOwner, {

                it.showSuccess?.let { list -> systemAdapter.replaceData(list) }

                it.showError?.let { message -> activity?.toast(message) }
            })
        }
    }
}