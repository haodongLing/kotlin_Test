package com.haodong.practice.mvvmcore.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * created by linghaoDo on 2020/6/23
 * description:
 *
 * version:
 */
class BaseViewModel : ViewModel() {
    open class UiState<T>(
        val isLoading: Boolean = false,
        val isRefresh: Boolean = false,
        val isSuccess: T? = null,
        val isError: String? = null
    )

    open class BaseUiModule<T>(
        var showLoading: Boolean = false,
        var showError: String? = null,
        var showSuccess: T? = null,
        var showEnd: Boolean = false,// 加载更多
        var isRefresh: Boolean = false // 刷新

    )
    fun launchOnUI(block:suspend CoroutineScope.()->Unit){
        viewModelScope.launch { block() }
    }
    suspend fun <T> launchIO(block: suspend CoroutineScope.() -> T){
        withContext(Dispatchers.IO){
            block
        }
    }
}