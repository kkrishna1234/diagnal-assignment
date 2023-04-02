package com.android.home.presentation

import androidx.recyclerview.widget.RecyclerView
import com.android.core.platform.AspectRatioHandler
import com.android.domain.model.Content
import com.android.home.databinding.ItemMovieBinding
import com.android.home.presentation.shared.MoviesImageViewHandler
import com.android.home.presentation.shared.MoviesImageViewHandlerImplementor
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MoviesRecyclerViewHolder(
    private val binding: ItemMovieBinding,
    private val moviesImageViewHandler: MoviesImageViewHandler = MoviesImageViewHandlerImplementor()
) : RecyclerView.ViewHolder(binding.root),
    MoviesImageViewHandler by moviesImageViewHandler,
    KoinComponent {
    private val aspectRatioHandler by inject<AspectRatioHandler>()

    init {
        moviesImageViewHandler.setMovieImageWidthAndHeight(binding.imageMovie, aspectRatioHandler)
    }

    fun bindData(data: Content) {
        binding.textMovieTitle.text = data.name
        moviesImageViewHandler.setImage(binding.imageMovie, data.posterImage)
    }
}
