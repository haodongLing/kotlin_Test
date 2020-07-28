package com.haodong.practice.kotlin.github.app.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.bennyhuo.common.ext.hideSoftInput
import com.haodong.practice.common.ErrorHandler
import com.haodong.practice.common.mvp.impl.BaseActivity
import com.haodong.practice.common.otherWise
import com.haodong.practice.common.yes
import com.haodong.practice.kotlin.github.app.R
import com.haodong.practice.kotlin.github.app.network.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity<LoginPresenter>() {
    private lateinit var mLoginProgress: ProgressBar
    private lateinit var mUsername: AutoCompleteTextView
    private lateinit var mPassword: EditText
    private lateinit var mSignInButton: Button
    private lateinit var mLoginForm: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mLoginProgress = findViewById<ProgressBar>(R.id.loginProgress)
        mUsername = findViewById<AutoCompleteTextView>(R.id.username)
        mPassword = findViewById<EditText>(R.id.password)
        mSignInButton = findViewById<Button>(R.id.signInButton)
        mLoginForm = findViewById<LinearLayout>(R.id.loginForm)

        mSignInButton.setOnClickListener {
            presenter.checkUserName(username.text.toString()).yes {
                presenter.checkPasswd(password.text.toString()).yes {
                    hideSoftInput()
                    presenter.doLogin(username.text.toString(), password.text.toString())
                }.otherWise {
                    showTips(password, "密码不合法")
                }
            }.otherWise {
                showTips(username, "账号不合法")
            }
        }

    }

    private fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
        mLoginForm.animate().setDuration(shortAnimTime.toLong()).alpha(
            (if (show) 0 else 1).toFloat()
        ).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mLoginForm.visibility = if (show) View.GONE else View.VISIBLE
            }
        })

        mLoginProgress.animate().setDuration(shortAnimTime.toLong()).alpha(
            (if (show) 1 else 0).toFloat()
        ).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mLoginProgress.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }

    private fun showTips(view: EditText, tips: String) {
        view.requestFocus()
        view.error = tips
    }

    fun onLoginStart() {
        showProgress(true)
    }

    fun onLoginError(e: Throwable) {
        e.printStackTrace()
        Log.e("s",ErrorHandler.handle(e).errorMessage)
        showProgress(false)
        toast("登陆失败")
    }

    fun loginSuccess() {
        showProgress(false)
        toast("登陆成功")
    }

    fun onDataInit(name: String, passWord: String) {
        username.setText(name)
        password.setText(passWord)
    }


}