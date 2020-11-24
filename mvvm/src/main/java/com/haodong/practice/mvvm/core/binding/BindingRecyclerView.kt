package com.haodong.practice.mvvm.core.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.haodong.practice.mvvm.core.view.SpaceItemDecoration

/**
 * created by linghaoDo on 2020/6/23
 * description:
 *
 * version:
 */
@BindingAdapter("itemTopPadding", "itemLeftPadding", "itemBottomPadding", "itemRightPadding", requireAll = false)
fun RecyclerView.addItemPadding(top: Int = 0, left: Int = 0, itemBottomPadding: Int = 0, itemRightPadding: Int = 0) {
    addItemDecoration(SpaceItemDecoration(top, left, itemBottomPadding, itemRightPadding))
}

@BindingAdapter("adapter")
fun RecyclerView.adapter(adapter: RecyclerView.Adapter<*>) {
    setAdapter(adapter)
}