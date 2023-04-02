package com.android.home.presentation.shared

import android.widget.ImageView
import com.android.core.platform.AspectRatioHandler

interface MoviesImageViewHandler {

    fun setMovieImageWidthAndHeight(view: ImageView?, aspectRatioHandler: AspectRatioHandler)

    fun setImage(imageView: ImageView?, imageName: String)
}
