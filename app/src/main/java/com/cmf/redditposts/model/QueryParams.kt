package com.cmf.redditposts.model

data class QueryParams(val limit: Int = 25, var after: String? = null)