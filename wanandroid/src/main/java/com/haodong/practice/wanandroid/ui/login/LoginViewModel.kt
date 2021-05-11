package com.haodong.practice.wanandroid.ui.login

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haodong.practice.mvvm.core.base.BaseViewModel
import com.haodong.practice.wanandroid.CoroutinesDispatcherProvider
import com.haodong.practice.wanandroid.checkResult
import com.haodong.practice.wanandroid.model.bean.User
import com.haodong.practice.wanandroid.model.repository.LoginRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * created by linghaoDo on 3/16/21
 * description:
 *
 * version:
 */
class LoginViewModel @ViewModelInject constructor(
    val repository: LoginRepository,
    val provider: CoroutinesDispatcherProvider
) : BaseViewModel() {
    val userName = ObservableField<String>("")
    val passWord = ObservableField<String>("")
    private val _uiState = MutableLiveData<LoginUiState<User>>()
    val uiState: LiveData<LoginUiState<User>>
        get() = _uiState

    private fun isInputValid(userName: String, passWord: String) = userName.isNotBlank() && passWord.isNotBlank()

    fun loginDataChanged() {
        _uiState.value = LoginUiState(enableLoginButton = isInputValid(userName.get() ?: "", passWord.get() ?: ""))
    }

    fun login() {
        launchOnUI {
            if (userName.get().isNullOrBlank() || passWord.get().isNullOrBlank()) {
                _uiState.value = LoginUiState(enableLoginButton = false)
                return@launchOnUI
            }
            _uiState.value = LoginUiState(isLoading = true)
            val result = repository.login(userName.get() ?: "", passWord.get() ?: "")
            result.checkResult(
                onSuccess = { _uiState.value = LoginUiState(isSuccess = it, enableLoginButton = true) },
                onError = {
                    _uiState.value = LoginUiState(isError = it, enableLoginButton = true)
                })
        }
    }

     fun loginFlow() {
        launchOnUI {
            repository.loginFlow(userName.get() ?: "", passWord.get()?:"").collect {
            _uiState.postValue(it)
        }
        }
    }


    fun register() {
      launchOnUI {
          repository.registerFlow(userName.get()?:"",passWord.get()?:"").collect {
              _uiState.postValue(it)
          }
      }
    }
    val verifyInput: (String) -> Unit = { loginDataChanged() }


}