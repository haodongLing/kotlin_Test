package com.haodong.practice.kotlin.github.app.network

import android.util.Log.VERBOSE
import com.haodong.practice.common.ensureDir
import com.haodong.practice.kotlin.github.app.AppContext
import com.haodong.practice.kotlin.github.app.network.interceptors.AcceptInterceptor
import com.haodong.practice.kotlin.github.app.network.interceptors.AuthInterceptor
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory2
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * created by linghaoDo on 2020/6/30
 * description:
 *
 * version:
 **/
private const val BASE_URL = "https://api.github.com"
private val cacheFile by lazy {
    File(AppContext.cacheDir, "webServiceApi").apply { ensureDir() }
}
val retrofit by lazy {
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory2.createWithSchedulers(
                Schedulers.io(),
                AndroidSchedulers.mainThread()
            )
        )
        .client(
            OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(Cache(cacheFile, 1024 * 1024 * 10))
                .addInterceptor(
                    LoggingInterceptor.Builder()
                        .setLevel(Level.BODY)
                        .log(VERBOSE)
                        .request("github")
                        .response("github")
                        .build()
                )
//                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(AcceptInterceptor())
                .addInterceptor(AuthInterceptor())
                .build()
        )
        .baseUrl(BASE_URL)
        .build()
}