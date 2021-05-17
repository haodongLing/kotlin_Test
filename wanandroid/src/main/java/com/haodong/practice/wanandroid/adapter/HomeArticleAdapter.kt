package com.haodong.practice.wanandroid.adapter

import com.haodong.practice.mvvm.core.util.Timer
import com.haodong.practice.wanandroid.APP_START
import com.haodong.practice.wanandroid.BR
import com.haodong.practice.wanandroid.R
import com.haodong.practice.wanandroid.model.bean.Article

/**
 * created by tangyuan on 2021/5/12
 * description:
 *
 */
class HomeArticleAdapter (layoutResId: Int = R.layout.item_article_constraint):BaseBindAdapter<Article>(layoutResId,BR.article) {
    private var showStar = true
    fun showStar(showStar: Boolean){
        this.showStar=showStar
    }

    override fun convert(helper: BindViewHolder, item: Article) {
        super.convert(helper, item)
        helper.addOnClickListener(R.id.articleStar)
        if (showStar)helper.setImageResource(R.id.articleStar,if (item.collect)R.drawable.timeline_like_pressed else R.drawable.timeline_like_normal)
        else
            helper.setVisible(R.id.articleStar,false)
        helper.setText(R.id.articleAuthor,if(item.author.isBlank())"分享者${item.shareUser}" else item.author)
        Timer.stop(APP_START)
    }
}