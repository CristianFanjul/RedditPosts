package com.cmf.redditposts.domain

import com.cmf.redditposts.model.ApiResponse
import com.cmf.redditposts.model.QueryParams
import javax.inject.Inject

interface IRedditRepository {
    suspend fun requestArticles(queryParams: QueryParams): Result<ApiResponse>
}

class RedditRepository @Inject constructor(
    private val remoteDataSource: IRedditDataSource
) : IRedditRepository {
    override suspend fun requestArticles(queryParams: QueryParams): Result<ApiResponse> =
        remoteDataSource.requestArticles(queryParams)
}