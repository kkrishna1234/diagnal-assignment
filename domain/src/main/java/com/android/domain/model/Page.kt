package com.android.domain.model

data class Page(
    val title: String,
    val totalItems: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val contentItems: ContentItems
)
