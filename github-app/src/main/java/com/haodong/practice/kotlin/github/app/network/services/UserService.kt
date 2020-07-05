package com.haodong.practice.kotlin.github.app.network.services

import com.haodong.practice.kotlin.github.app.network.entities.User
import com.haodong.practice.kotlin.github.app.network.retrofit
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * created by linghaoDo on 2020/7/5
 * description:
 *
 * version:
 */
interface UserApi {
    @GET("/User")
    fun getAuthenticatedUser(): Observable<User>
}

object UserService : UserApi by retrofit.create(UserApi::class.java)