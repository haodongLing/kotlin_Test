package com.haodong.practice.kotlin.github.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.haodong.practice.common.mvp.impl.BaseActivity
import com.haodong.practice.kotlin.github.app.R
import com.haodong.practice.kotlin.github.app.network.presenter.LoginPresenter

class LoginActivity : BaseActivity<LoginPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}