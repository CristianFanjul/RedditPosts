package com.cmf.redditposts

import com.cmf.redditposts.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class RedditPosts : DaggerApplication() {

    companion object {
        lateinit var instance: RedditPosts
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this)!!.build()!!
    }
}