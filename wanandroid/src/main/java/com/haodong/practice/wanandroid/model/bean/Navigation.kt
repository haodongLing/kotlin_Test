package com.haodong.practice.wanandroid.model.bean

import com.haodong.practice.wanandroid.model.bean.Article

/**
 * Created by Lu
 * on 2018/3/28 21:22
 */
data class Navigation(val articles: List<Article>,
                      val cid: Int,
                      val name: String)