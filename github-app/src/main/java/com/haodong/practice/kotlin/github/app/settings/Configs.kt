package com.haodong.practice.kotlin.github.app.settings

import com.bennyhuo.common.log.logger
import com.haodong.practice.kotlin.github.app.AppContext
import com.haodong.practice.kotlin.github.app.utils.deviceId

/**
 * created by linghaoDo on 2020/6/29
 * description:
 *
 * version:
 */
object Configs {
    object Account{
        val SCOPES = listOf("user", "repo", "notifications", "gist", "admin:org")
        const val clientId="ebb2fdec43640c6ae828"
        const val clientSecret="5c776c60c74cc3dc06f79434dfc30469777ba1f5"
        const val note="kotliner.cn"
        const val noteUrl="http://www.kotliner.cn"
        val fingerPrint by lazy{
            (AppContext.deviceId + clientId).also { logger.info("fingerPrint: "+it) }
        }
    }
}