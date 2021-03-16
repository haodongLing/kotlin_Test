package com.haodong.practice.wanandroid.model.repository

import com.haodong.practice.wanandroid.model.api.BaseRepository
import com.haodong.practice.wanandroid.model.api.WanRetrofitClient
import com.haodong.practice.wanandroid.model.bean.ArticleList
import java.io.IOException
import com.haodong.practice.mvvm.core.Result
import com.haodong.practice.wanandroid.util.isSuccess

/**
 * Created by luyao
 * on 2019/10/15 10:33
 */
class SquareRepository :  BaseRepository(){


    suspend fun getSquareArticleList(page:Int): Result<ArticleList> {
        return safeApiCall(call = {requestSquareArticleList(page)},errorMessage = "网络异常")
    }

    private suspend fun requestSquareArticleList(page: Int): Result<ArticleList> {
        val response = WanRetrofitClient.service.getSquareArticleList(page)
        return if (response.isSuccess()) Result.Success(response.data)
        else Result.Error(IOException(response.errorMsg))

    }
}