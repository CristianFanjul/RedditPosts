package com.cmf.redditposts.ui.main.paging

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cmf.redditposts.R
import com.cmf.redditposts.RedditPosts
import com.cmf.redditposts.misc.ImageLoader
import com.cmf.redditposts.model.Article
import com.cmf.redditposts.ui.main.IOnArticleListener
import kotlinx.android.synthetic.main.recycler_item_article.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.util.*


class ArticleViewHolder(
    itemView: View,
    private val listener: IOnArticleListener
) : RecyclerView.ViewHolder(itemView) {

    fun setValues(item: Article) {
        itemView.txt_vw_username.text = item.author
        val timeAgo = getTimeAgo(item)
        itemView.txt_vw_time.text = timeAgo
        itemView.txt_vw_comments.text =
            RedditPosts.instance.getString(R.string.comments_number, item.comments)
        itemView.txt_vw_article_title.text = item.title ?: ""
        itemView.img_vw_read_bubble.isVisible = !item.read

        itemView.vw_article_item_root.setOnClickListener { listener.onArticleClicked(item) }
        itemView.txt_vw_dismiss_post.setOnClickListener { listener.onArticleDismissed(item) }
        itemView.img_vw_dismiss_post.setOnClickListener { listener.onArticleDismissed(item) }

        item.thumbnail?.let { ImageLoader.loadImage(it, itemView.img_vw_article_image) }
    }

    private fun getTimeAgo(item: Article): CharSequence {
        val prettyTime = PrettyTime(Locale.getDefault())
        val unixToMills = (item.created_utc ?: 0) * 1000
        return prettyTime.format(Date(unixToMills))
    }
}