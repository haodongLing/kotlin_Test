package com.haodong.practice.kotlin.github.app.network.interceptors

import android.util.Base64
import com.haodong.practice.kotlin.github.app.model.account.AccountManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * created by linghaoDo on 2020/7/5
 * description:
 *
 * version:
 */
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request();
        return chain.proceed(original.newBuilder().apply {
            when {
                original.url.pathSegments.contains("authorizations") -> {
                    val userCredentials = AccountManager.username + ":" + AccountManager.passwd
                    val auth = "Basic " + String(Base64.encode(userCredentials.toByteArray(), Base64.DEFAULT)).trim()
                    header("Authorization", auth)
                }
                AccountManager.isLoggedIn() -> {
                    val auth = "Token" + AccountManager.token
                    header("Authorization", auth)
                }
                else -> removeHeader("Authorization")
            }
        }.build())

    }
}