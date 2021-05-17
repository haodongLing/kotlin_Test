package com.haodong.practice.wanandroid.ui.project

import android.os.Bundle
import androidx.lifecycle.Observer
import com.haodong.practice.mvvm.core.base.BaseVMFragment
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.adapter.BaseBindAdapter
import com.haodong.practice.wanandroid.databinding.FragmentProjecttypeBinding
import com.haodong.practice.wanandroid.model.bean.Article
import com.haodong.practice.wanandroid.ui.square.ArticleViewModel
import com.haodong.practice.wanandroid.util.Preference
import kotlinx.android.synthetic.main.fragment_projecttype.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.databinding.library.baseAdapters.BR

/**
 * created by tangyuan on 2021/5/17
 * description:
 *
 */
class ProjectTypeFragment : BaseVMFragment<FragmentProjecttypeBinding>(R.layout.fragment_projecttype) {
    private val articleViewModel by viewModel<ArticleViewModel>()

    private val isLogin by Preference(Preference.IS_LOGIN, false)
    private val cid by lazy { arguments?.getInt(CID) }
    private val isLasted by lazy { arguments?.getBoolean(LASTED) } // 区分是最新项目 还是项目分类
    private val projectAdapter by lazy { BaseBindAdapter<Article>(R.layout.item_project, BR.article) }

    companion object {
        private const val CID = "projectCid"
        private const val LASTED = "lasted"
        fun newInstance(cid: Int, isLasted: Boolean): ProjectTypeFragment {
            val fragmet = ProjectTypeFragment()
            val bundle = Bundle()
            bundle.putInt(CID, cid)
            bundle.putBoolean(LASTED, isLasted)
            fragmet.arguments = bundle
            return fragmet
        }
    }

    override fun initView() {
        binding.adapter = projectAdapter
        initRecyclerView()
    }

    private fun initRecyclerView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        loadData(false)
    }

    override fun startObserve() {
       articleViewModel.uiState.observe(viewLifecycleOwner, Observer {
           projectRefreshLayout.isRefreshing=it.showLoading
           it.showSuccess?.let { list->
               projectAdapter.run{
                   if (it.isRefresh){
                       replaceData(list.datas)
                   }else{
                       addData(list.datas)
                   }

               }
           }
       })
    }

    private fun loadData(isRefresh: Boolean) {
        isLasted?.run {
            if (this) {
                articleViewModel.getLatestProjectList(isRefresh)
            } else {
                cid?.let {
                    articleViewModel.getProjectTypeDetailList(isRefresh, it)
                }
            }
        }
    }

}