package com.cmf.redditposts.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmf.redditposts.domain.IRedditDataSource
import com.cmf.redditposts.domain.IRedditRepository
import com.cmf.redditposts.domain.RedditRemoteDataSource
import com.cmf.redditposts.domain.RedditRepository
import com.cmf.redditposts.ui.detail.DetailViewModel
import com.cmf.redditposts.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BindingsModule {
    @Binds
    fun bindRedditRepository(repository: RedditRepository): IRedditRepository

    @Binds
    fun bindRedditDataSource(dataSource: RedditRemoteDataSource): IRedditDataSource

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindsHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun bindsDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}