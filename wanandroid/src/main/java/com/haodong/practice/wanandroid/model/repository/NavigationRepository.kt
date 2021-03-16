package com.haodong.practice.wanandroid.model.repository

import com.haodong.practice.wanandroid.model.api.BaseRepository
import com.haodong.practice.wanandroid.model.api.WanRetrofitClient
import com.haodong.practice.wanandroid.model.bean.Navigation
import com.haodong.practice.mvvm.core.Result

/**
 * Created by luyao
 * on 2019/4/10 14:15
 */
class NavigationRepository : BaseRepository() {


    suspend fun getNavigation(): Result<List<Navigation>> {
        return safeApiCall(call = { requestNavigation() }, errorMessage = "获取数据失败")
    }


    private suspend fun requestNavigation(): Result<List<Navigation>> =
            executeResponse(WanRetrofitClient.service.getNavigation())
}