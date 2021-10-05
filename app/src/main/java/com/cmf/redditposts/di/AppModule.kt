package com.cmf.redditposts.di

import com.cmf.redditposts.domain.IRedditDataSource
import com.cmf.redditposts.domain.IRedditRepository
import com.cmf.redditposts.domain.RedditRemoteDataSource
import com.cmf.redditposts.domain.RedditRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindRedditRepository(repository: RedditRepository): IRedditRepository

    @Singleton
    @Binds
    abstract fun bindRedditDataSource(dataSource: RedditRemoteDataSource): IRedditDataSource

}