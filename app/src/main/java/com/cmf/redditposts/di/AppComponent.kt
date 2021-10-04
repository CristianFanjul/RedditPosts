package com.cmf.redditposts.di

import android.app.Application
import com.cmf.redditposts.RedditPosts
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AndroidSupportInjectionModule::class,
        BindingsModule::class,
        ContributorBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<RedditPosts> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?

        fun build(): AppComponent?
    }
}