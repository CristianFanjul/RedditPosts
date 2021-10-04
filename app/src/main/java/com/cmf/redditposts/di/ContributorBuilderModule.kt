package com.cmf.redditposts.di

import com.cmf.redditposts.ui.detail.DetailFragment
import com.cmf.redditposts.ui.detail.DetailViewModel
import com.cmf.redditposts.ui.home.HomeFragment
import com.cmf.redditposts.ui.home.HomeViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributorBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainViewModel(): HomeViewModel

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailViewModel(): DetailViewModel

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment
}