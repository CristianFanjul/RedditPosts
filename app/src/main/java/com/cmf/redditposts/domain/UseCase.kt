package com.cmf.redditposts.domain

abstract class UseCase<out Type, in Params> {
    abstract suspend operator fun invoke(params: Params): Result<Type>
}