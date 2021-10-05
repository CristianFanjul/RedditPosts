package com.cmf.redditposts.ui.main

import com.cmf.redditposts.model.Article

interface IOnArticleListener {
    fun onArticleClicked(item: Article, position: Int)
    fun onArticleDismissed(item: Article, position: Int)
}