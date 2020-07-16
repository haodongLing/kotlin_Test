package com.haodong.practice.kotlin.github.app.network.presenter

import android.widget.EditText
import com.haodong.practice.common.mvp.impl.BasePresenter
import com.haodong.practice.kotlin.github.app.BuildConfig
import com.haodong.practice.kotlin.github.app.model.account.AccountManager
import com.haodong.practice.kotlin.github.app.view.LoginActivity

/**
 * created by linghaoDo on 2020/7/5
 * description:
 *
 * version:
 */
class LoginPresenter : BasePresenter<LoginActivity>() {
    fun doLogin(name: String, passwd: String) {
        AccountManager.username = name
        AccountManager.passwd = passwd
        view.onLoginStart()
        AccountManager.login()
            .subscribe({
                view.loginSuccess()
            }, { view.onLoginError(it) })
    }

    fun checkUserName(name: String): Boolean {
        return true
    }

    fun checkPasswd(passwd: String): Boolean {
        return true
    }

    private fun showTips(view: EditText, tips: String) {

    }

    fun loginStart() {

    }

    fun loginError(e: Throwable) {

    }

    fun loginSuccess() {
    }

    override fun onResume() {
        super.onResume()
        if(BuildConfig.DEBUG){
            view.onDataInit(BuildConfig.testUserName, BuildConfig.testPassword)
        } else {
            view.onDataInit(AccountManager.username, AccountManager.passwd)
        }
    }
}