package com.haodong.practice.wanandroid.ui.login

import com.haodong.practice.mvvm.core.base.BaseViewModel

/**
 * created by linghaoDo on 3/16/21
 * description:
 *
 * version:
 */
class LoginUiState<T>(
    isLoading: Boolean = false,
    isSuccess: T? = null,
    isError: String? = null,
    val enableLoginButton: Boolean = false,
    val needLogin: Boolean = false
) : BaseViewModel.UiState<T>(isLoading, false, isSuccess, isError) {
}