package com.haodong.practice.wanandroid.model.repository

import com.haodong.practice.wanandroid.model.api.BaseRepository
import com.haodong.practice.wanandroid.model.api.WanRetrofitClient
import com.haodong.practice.wanandroid.model.bean.ArticleList
import com.haodong.practice.wanandroid.model.bean.Hot
import com.haodong.practice.mvvm.core.Result

/**
 * Created by luyao
 * on 2019/4/10 14:26
 */
class SearchRepository : BaseRepository() {
    suspend fun searchHot(page: Int, key: String): Result<ArticleList> {
        return safeApiCall(call = {requestSearch(page, key)},errorMessage = "网络错误")
    }

    suspend fun getWebSites(): Result<List<Hot>> {
        return safeApiCall(call = {requestWebSites()},errorMessage = "网络错误")
    }

    suspend fun getHotSearch(): Result<List<Hot>> {
        return safeApiCall(call = {requestHotSearch()},errorMessage = "网络错误")
    }

    private suspend fun requestWebSites(): Result<List<Hot>> =
            executeResponse(WanRetrofitClient.service.getWebsites())

    private suspend fun requestHotSearch(): Result<List<Hot>> =
            executeResponse(WanRetrofitClient.service.getHot())

    private suspend fun requestSearch(page: Int, key: String): Result<ArticleList> =
            executeResponse(WanRetrofitClient.service.searchHot(page, key))
}