package com.cmf.redditposts

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RedditPostsApp : Application() {

    companion object {
        lateinit var instance: RedditPostsApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}