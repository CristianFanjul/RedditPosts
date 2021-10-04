package com.cmf.redditposts.domain

import com.cmf.redditposts.model.ApiResponse
import com.cmf.redditposts.model.QueryParams
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val redditRepository: IRedditRepository
) : UseCase<ApiResponse, QueryParams>() {
    override suspend fun invoke(params: QueryParams): Result<ApiResponse> {
        return try {
            redditRepository.requestArticles(params)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}