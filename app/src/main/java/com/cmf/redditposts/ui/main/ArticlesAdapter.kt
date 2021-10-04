package com.cmf.redditposts.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cmf.redditposts.R
import com.cmf.redditposts.model.Article
import com.cmf.redditposts.ui.main.paging.ArticleViewHolder

class ArticlesAdapter(private val listener: IOnArticleListener) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    private val items: MutableList<Article> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_article, parent, false)
        return ArticleViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: ArticleViewHolder, position: Int) {
        val vm = items[position]
        viewHolder.setValues(vm)
    }

    fun setItems(list: List<Article>) {
        items.clear()
        items.addAll(list)
        notifyItemRangeChanged(0, items.size - 1)
    }

    fun addItems(list: List<Article>) {
        items.addAll(list)
        notifyItemRangeChanged(items.size, items.size + list.size - 1)
    }

    fun getItems(): MutableList<Article> = items
}
