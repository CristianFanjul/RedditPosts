package com.cmf.redditposts.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(val data: Data?)

data class Data(val children: List<DataChildren>?, val after: String?, val before: String?)

data class DataChildren(@SerializedName("data") val article: Article?)