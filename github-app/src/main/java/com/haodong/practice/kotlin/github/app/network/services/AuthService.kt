package com.haodong.practice.kotlin.github.app.network.services

import com.haodong.practice.kotlin.github.app.network.entities.AuthorizationReq
import com.haodong.practice.kotlin.github.app.network.entities.AuthorizationRsp
import com.haodong.practice.kotlin.github.app.network.retrofit
import com.haodong.practice.kotlin.github.app.settings.Configs
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * created by linghaoDo on 2020/6/30
 * description:
 *
 * version:
 */
interface AuthApi {
    @PUT("/authorizations/clients/${Configs.Account.clientId}/{fingerPrint}")
    fun createAuthorization(@Body req: AuthorizationReq, @Path("fingerPrint") fingerPrint: String = Configs.Account.fingerPrint)
            : Observable<AuthorizationRsp>

    @DELETE("/authorizations/{id}")
    fun deleteAuthorization(@Path("id") id: Int): Observable<Response<Any>>
}

object AuthService : AuthApi by retrofit.create(AuthApi::class.java)