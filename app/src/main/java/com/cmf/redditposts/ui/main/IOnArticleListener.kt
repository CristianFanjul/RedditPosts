package com.cmf.redditposts.ui.main

import com.cmf.redditposts.model.Article

interface IOnArticleListener {
    fun onArticleClicked(item: Article)
    fun onArticleDismissed(item: Article)
}