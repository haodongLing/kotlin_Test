package com.haodong.practice.kotlin.github.app.utils

import androidx.constraintlayout.widget.Placeholder
import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * created by linghaoDo on 2020/7/28
 * description:
 *
 * version:
 */
fun AvatarImageView.loadWithGlide(
    url: String,
    textPlaceholder: Char,
    requestOptions: RequestOptions = RequestOptions()
) {
    textPlaceholder.toString()
        .let {
            setTextAndColorSeed(it.toUpperCase(), it.hashCode().toString())
        }
    Glide.with(this.context)
        .load(url)
        .apply(requestOptions)
        .into(this)
}