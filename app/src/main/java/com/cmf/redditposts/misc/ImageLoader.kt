package com.cmf.redditposts.misc

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cmf.redditposts.RedditPosts

object ImageLoader {
    fun loadImage(thumbnail: String, view: ImageView) {
        Glide.with(RedditPosts.instance.applicationContext)
            .load(thumbnail)
            .centerCrop()
            .into(view)
    }
}
