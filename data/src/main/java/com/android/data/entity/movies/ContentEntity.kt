package com.android.data.entity.movies

import com.google.gson.annotations.SerializedName

data class ContentEntity(

    val name: String,

    @SerializedName("poster-image")
    val posterImage: String
)
