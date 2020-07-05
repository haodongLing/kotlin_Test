package com.haodong.practice.kotlin.github.app.network.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * created by linghaoDo on 2020/7/5
 * description:
 *
 * version:
 */
class AccountInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request();
        return chain.proceed(original.newBuilder().apply {
            header("accept", "application/vnd.github.v3.full+json, ${original.header("accept") ?: ""}")
        }.build())
    }

}
