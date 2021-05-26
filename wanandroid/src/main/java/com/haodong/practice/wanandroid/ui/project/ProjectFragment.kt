package com.haodong.practice.wanandroid.ui.project

import com.haodong.practice.mvvm.core.base.BaseVMFragment
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.databinding.ActivityProjectBinding

/**
 * created by tangyuan on 2021/5/17
 * description:
 *
 */
open class ProjectFragment:BaseVMFragment<ActivityProjectBinding>(R.layout.activity_project) {

    open var isBlog = false // 区分是公众号还是项目分类

    override fun initView() {
    }

    override fun initData() {
    }

    override fun startObserve() {
    }
}