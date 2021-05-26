package com.haodong.practice.wanandroid.ui.square

import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.Navigation
import com.haodong.practice.ktx.ext.startKtxActivity
import com.haodong.practice.ktx.ext.toast
import com.haodong.practice.mvvm.core.base.BaseVMFragment
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.adapter.BaseBindAdapter
import com.haodong.practice.wanandroid.databinding.FragmentSquareBinding
import com.haodong.practice.wanandroid.model.bean.Article
import com.haodong.practice.wanandroid.ui.BrowserActivity
import com.haodong.practice.wanandroid.ui.view.CustomLoadMoreView
import kotlinx.android.synthetic.main.fragment_square.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * created by tangyuan on 2021/5/14
 * description:
 *
 */
class SquareFragment : BaseVMFragment<FragmentSquareBinding>(R.layout.fragment_square) {
    private val articleViewModel by viewModel<ArticleViewModel>()
    private val squareAdapter by lazy {
        BaseBindAdapter<Article>(R.layout.item_square_constraint, BR.article)
    }

    override fun initView() {
        binding.run {
            viewModel = articleViewModel
            adapter = squareAdapter
        }
        initRecycleView()

    }

    override fun initData() {
        refresh()
    }

    private fun initRecycleView() {
        squareAdapter.run {
            setOnItemClickListener { _, _, position ->
                startKtxActivity<BrowserActivity>(value = BrowserActivity.URL to squareAdapter.data[position].link)
            }
            setLoadMoreView(CustomLoadMoreView())
            setOnLoadMoreListener({ loadMore() }, squareRecycleView)
        }
    }

    override fun startObserve() {
        articleViewModel.uiState.observe(viewLifecycleOwner, {
            it.showSuccess?.let { list ->
                squareAdapter.run {
                    setEnableLoadMore(true)
                    if (it.isRefresh) {
                        replaceData(list.datas)
                    } else {
                        addData(list.datas)
                    }
                    setEnableLoadMore(true)
                    loadMoreComplete()
                }
            }
            if (it.showEnd) squareAdapter.loadMoreEnd()
            it.needLogin?.let { needLogin ->
                if (needLogin) {
                    Navigation.findNavController(squareRecycleView).navigate(R.id.action_tab_to_login)
                } else {
                    Navigation.findNavController(squareRecycleView).navigate(R.id.action_tab_to_share)
                }
                it.showError?.let { message ->
                    activity?.toast(if (message.isBlank()) "网络异常" else message)
                }
            }
        })
    }

    private fun loadMore() {
        articleViewModel.getSquareArticleList(false)
    }

    fun refresh() {
        articleViewModel.getSquareArticleList(true)
    }
}