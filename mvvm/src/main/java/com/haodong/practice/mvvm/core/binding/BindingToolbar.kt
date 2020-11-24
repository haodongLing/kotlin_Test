package com.haodong.practice.mvvm.core.binding

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

/**
 * created by linghaoDo on 2020/9/17
 * description:
 *
 * version:
 */
@BindingAdapter("title", "icon", "navigationClick", requireAll = false)
fun Toolbar.init(titleResId: Int, iconResId: Int, action: () -> Unit) {
    setTitle(titleResId)
    setNavigationIcon(iconResId)
    setNavigationOnClickListener { action }
}