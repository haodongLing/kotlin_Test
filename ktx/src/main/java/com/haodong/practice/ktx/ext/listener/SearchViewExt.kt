package com.haodong.practice.ktx.ext.listener

import androidx.appcompat.widget.SearchView

/**
 * created by linghaoDo on 2020/7/17
 * description:
 *
 * version:
 */
//fun SearchView.queryTextListenr(listener:KtxQ)
class KtxQueryTextListener : SearchView.OnQueryTextListener {
    private var _onQueryTextSubmit: ((String?) -> Unit)? = null
    private var _onQueryTextChange: ((String?) -> Unit)? = null
    fun onQueryTextSubmit(listener: ((String?) -> Unit)?) {
        _onQueryTextSubmit = listener
    }

    fun onQueryTextChange(listener: ((String?) -> Unit)?) {
        _onQueryTextChange = listener
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        _onQueryTextSubmit?.invoke(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        _onQueryTextChange?.invoke(newText)
        return false
    }
}