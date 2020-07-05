package com.haodong.practice.kotlin.github.app.model.account

import android.accounts.AccountsException
import com.google.gson.Gson
import com.haodong.practice.kotlin.github.app.network.entities.AuthorizationReq
import com.haodong.practice.kotlin.github.app.network.entities.AuthorizationRsp
import com.haodong.practice.kotlin.github.app.network.entities.User
import com.haodong.practice.kotlin.github.app.network.services.AuthService
import com.haodong.practice.kotlin.github.app.network.services.UserService
import com.haodong.practice.kotlin.github.app.utils.fromJson
import com.haodong.practice.kotlin.github.app.utils.pref
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

/**
 * created by linghaoDo on 2020/7/5
 * description:
 *
 * version:
 */
interface OnAccountStateChangeListener {
    fun onLogin(user: User)

    fun onLogout()
}

object AccountManager {
    var authId by pref(-1)
    var username by pref("")
    var passwd by pref("")
    var token by pref("")
    private var userJson by pref("")

    var currentUser: User? = null
        get() {
            if (field == null && userJson.isNotEmpty()) {
                field = Gson().fromJson<User>(userJson)
            }
            return field
        }
        set(value) {
            if (value == null) {
                userJson = ""
            } else {
                userJson = Gson().toJson(value)
            }
            field = value
        }
    val onAccountStateChangeListeners = ArrayList<OnAccountStateChangeListener>()

    private fun notifyLogin(user: User) {
        onAccountStateChangeListeners.forEach {
            it.onLogin(user)
        }
    }

    private fun notifyLogout() {
        onAccountStateChangeListeners.forEach { it.onLogout() }
    }

    fun isLoggedIn(): Boolean = token.isNotEmpty()
    fun login() = AuthService.createAuthorization(AuthorizationReq())
        .observeOn(Schedulers.io())
        .doOnNext { if (it.token.isEmpty()) throw AccountException(it) }
        .retryWhen {
            it.flatMap {
                if (it is AccountException) {
                    AuthService.deleteAuthorization(it.authorizationRsp.id)
                } else {
                    Observable.error(it)
                }
            }
        }
        .flatMap {
            token = it.token
            authId = it.id
            UserService.getAuthenticatedUser()
        }
        .map {
            currentUser = it
            notifyLogin(it)
        }

    fun logout() = AuthService.deleteAuthorization(authId)
        .doOnNext {
            if (it.isSuccessful) {
                authId = -1
                token = ""
                currentUser = null
                notifyLogout()
            } else {
                throw HttpException(it)
            }
        }
    class AccountException(val authorizationRsp: AuthorizationRsp) : Exception("Already logged in.")
}
