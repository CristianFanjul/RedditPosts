package com.cmf.redditposts.retrofit

import com.cmf.redditposts.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiClient {
    @GET("top.json")
    suspend fun requestArticles(
        @Query("limit") limit: Int = 25,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): ApiResponse
}