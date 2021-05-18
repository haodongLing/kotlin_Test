package com.haodong.practice.wanandroid.model.repository

import com.google.gson.Gson
import com.haodong.practice.wanandroid.App
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.model.api.BaseRepository
import com.haodong.practice.wanandroid.model.api.WanService
import com.haodong.practice.wanandroid.model.bean.User
import com.haodong.practice.wanandroid.util.Preference
import com.haodong.practice.mvvm.core.Result
import com.haodong.practice.wanandroid.model.bean.doError
import com.haodong.practice.wanandroid.model.bean.doSuccess
import com.haodong.practice.wanandroid.ui.login.LoginUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart


/**
 * Created by luyao
 * on 2019/4/10 9:42
 */

class LoginRepository  (val service: WanService) : BaseRepository() {

    private var isLogin by Preference(Preference.IS_LOGIN, false)
    private var userJson by Preference(Preference.USER_GSON, "")


    suspend fun login(userName: String, passWord: String): Result<User> {
        return safeApiCall(
            call = { requestLogin(userName, passWord) },
            errorMessage = App.CONTEXT.getString(R.string.about)
        )
    }

    @ExperimentalCoroutinesApi
    suspend fun loginFlow(userName: String, passWord: String) = flow {
        if (userName.isBlank() || passWord.isBlank()) {
            emit(LoginUiState(enableLoginButton = false))
            return@flow
        }
        service.login(userName, passWord).doSuccess { user ->
            isLogin = true
            userJson = Gson().toJson(user)
            App.CURRENT_USER = user
            emit(LoginUiState(isSuccess = user, enableLoginButton = true))
        }.doError { errorMsg ->
            emit(LoginUiState<User>(isError = errorMsg, enableLoginButton = true))
        }

    }.onStart {
        emit(LoginUiState(isLoading = true))
    }.flowOn(Dispatchers.IO).catch {
        emit(LoginUiState(isError = it.message, enableLoginButton = true))
    }


    private suspend fun requestLogin(userName: String, passWord: String): Result<User> {
        val response = service.login(userName, passWord)

        return executeResponse(response, {
            val user = response.data
            isLogin = true
            userJson = Gson().toJson(user)
            App.CURRENT_USER = user
        })
    }

    suspend fun register(userName: String, passWord: String): Result<User> {
        return safeApiCall(call = { requestRegister(userName, passWord) }, errorMessage = "注册失败")
    }

    private suspend fun requestRegister(userName: String, passWord: String): Result<User> {
        val response = service.register(userName, passWord, passWord)
        return executeResponse(response, { requestLogin(userName, passWord) })
    }

    suspend fun registerFlow(username: String, passWord: String) = flow<LoginUiState<User>> {
        if (username.isBlank() || passWord.isBlank()) {
            emit(LoginUiState(enableLoginButton = false))
            return@flow
        }
        service.register(username, passWord,passWord).doSuccess {
            emit(LoginUiState(needLogin = true))

        }.doError { errorMsg ->
            emit(LoginUiState(isError = errorMsg, enableLoginButton = false))
        }

    }.onStart {
        emit(LoginUiState(isLoading = true))
    }.flowOn(Dispatchers.IO).catch { emit(LoginUiState(isError = it.message, enableLoginButton = true)) }

}