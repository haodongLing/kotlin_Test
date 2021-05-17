package com.haodong.practice.wanandroid.ui.home

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.haodong.practice.ktx.ext.dp2px
import com.haodong.practice.mvvm.core.base.BaseVMFragment
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.adapter.HomeArticleAdapter
import com.haodong.practice.wanandroid.databinding.FragmentHomeBinding
import com.haodong.practice.wanandroid.model.bean.Banner
import com.haodong.practice.wanandroid.ui.BrowserActivity
import com.haodong.practice.wanandroid.ui.square.ArticleViewModel
import com.haodong.practice.wanandroid.ui.view.CustomLoadMoreView
import com.haodong.practice.wanandroid.util.Preference
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*
import luyao.wanandroid.util.GlideImageLoader
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * created by tangyuan on 2021/5/11
 * description:
 *
 */
class HomeFragment : BaseVMFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val articleViewModel by viewModel<ArticleViewModel>()
    private val isLogin by Preference(Preference.IS_LOGIN, false)
    private val homeArticleAdapter by lazy { HomeArticleAdapter() }
    private val bannerImages = mutableListOf<String>()
    private val bannerTitles = mutableListOf<String>()
    private val bannerUrls = mutableListOf<String>()
    private val banner by lazy { com.youth.banner.Banner(activity) }


    override fun initView() {
        binding.apply {
            viewModel = articleViewModel
            adapter = homeArticleAdapter
        }
        initRecyclerView()
        initBanner()
    }

    private fun initBanner() {
        banner.run {
            layoutParams=LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,banner.dp2px(200))
            setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            setImageLoader(GlideImageLoader())
            setOnBannerListener({
                position ->
                run{
                    androidx.navigation.Navigation.findNavController(banner).navigate(R.id.action_tab_to_browser, bundleOf(
                        BrowserActivity.URL to bannerUrls[position]))
                }
            })
        }
    }

    private fun initRecyclerView() {
        homeArticleAdapter.run {
            setOnItemChildClickListener { adapter, view, position ->
//                val bundle=bun
            }
            onItemChildClickListener = this@HomeFragment.onItemChildClickListener
            if(headerLayoutCount>0)
                removeAllHeaderView()
            setLoadMoreView(CustomLoadMoreView())
            setOnLoadMoreListener({loadMore()},homeRecycleView)
        }
    }

    private val onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
        when (view.id) {
            R.id.articleStar -> {
                if (isLogin){
                    homeArticleAdapter.let {
                        it->
                        it.data[position].run {
                            collect=!collect
                            articleViewModel.collectArticle(id,collect)
                        }
                    }
                }
            }
        }
    }
    private fun loadMore(){
        articleViewModel.getHomeArticleList(false)
    }

    override fun initData() {
        refresh()
    }

    override fun startObserve() {
        articleViewModel.apply {
            mBanners.observe(viewLifecycleOwner, Observer {
                it?.let {
                    setBanner(it)
                }
            })
        }
    }
    private fun setBanner(bannerList: List<Banner>) {
        for (banner in bannerList) {
            bannerImages.add(banner.imagePath)
            bannerTitles.add(banner.title)
            bannerUrls.add(banner.url)
        }
        banner.setImages(bannerImages)
            .setBannerTitles(bannerTitles)
            .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            .setDelayTime(3000)
        banner.start()
    }

    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }


    fun refresh() {
        articleViewModel.getHomeArticleList(true)
    }

}