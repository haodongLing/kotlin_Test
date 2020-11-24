package com.haodong.practice.mvvm.core.binding

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * created by linghaoDo on 2020/9/17
 * description:
 *
 * version:
 */
@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}