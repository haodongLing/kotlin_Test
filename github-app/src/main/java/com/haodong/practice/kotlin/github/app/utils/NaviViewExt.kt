package com.bennyhuo.github.utils


import android.view.View
import androidx.core.view.ViewCompat

import com.google.android.material.navigation.NavigationView
import com.haodong.practice.common.otherWise
import com.haodong.practice.common.yes

/**
 * Created by benny on 7/6/17.
 */
inline fun NavigationView.doOnLayoutAvailable(crossinline block: () -> Unit) {
    ViewCompat.isLaidOut(this).yes {
        block()
    }.otherWise {
        addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                removeOnLayoutChangeListener(this)
                block()
            }
        })
    }
}


