package com.haodong.practice.wanandroid.ui.view

import com.chad.library.adapter.base.loadmore.LoadMoreView
import com.haodong.practice.wanandroid.R

/**
 * created by tangyuan on 2021/5/14
 * description:
 *
 */
class CustomLoadMoreView :LoadMoreView() {
    override fun getLayoutId(): Int {
        return R.layout.view_load_more
    }

    override fun getLoadingViewId(): Int {
        return R.id.load_more_loading_view
    }

    override fun getLoadFailViewId(): Int {
        return R.id.load_more_load_fail_view
    }

    override fun getLoadEndViewId(): Int {
        return R.id.load_more_load_end_view
    }
}