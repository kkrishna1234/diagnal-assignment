package com.android.data.entity.movies

import com.google.gson.annotations.SerializedName

data class PageEntity(
    val title: String,

    @SerializedName("total-content-items")
    val totalItems: Int,

    @SerializedName("page-num")
    val pageNumber: Int,

    @SerializedName("page-size")
    val pageSize: Int,

    @SerializedName("content-items")
    val contentItems: ContentItemsEntity
)
