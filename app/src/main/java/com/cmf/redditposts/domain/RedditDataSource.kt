package com.cmf.redditposts.domain

import com.cmf.redditposts.model.ApiResponse
import com.cmf.redditposts.model.QueryParams
import com.cmf.redditposts.retrofit.IApiClient
import javax.inject.Inject

interface IRedditDataSource {
    suspend fun requestArticles(queryParams: QueryParams): Result<ApiResponse>
}

class RedditRemoteDataSource @Inject constructor(
    private val redditClient: IApiClient
) : IRedditDataSource {

    override suspend fun requestArticles(queryParams: QueryParams): Result<ApiResponse> {
        return Result.Success(redditClient.requestArticles(queryParams.limit, queryParams.after))
    }
}