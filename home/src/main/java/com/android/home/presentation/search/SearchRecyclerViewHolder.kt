package com.android.home.presentation.search

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.recyclerview.widget.RecyclerView
import com.android.core.platform.AspectRatioHandler
import com.android.domain.model.Content
import com.android.home.databinding.ItemMovieBinding
import com.android.home.presentation.shared.MoviesImageViewHandler
import com.android.home.presentation.shared.MoviesImageViewHandlerImplementor
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchRecyclerViewHolder(
    private val binding: ItemMovieBinding,
    private val moviesImageViewHandler: MoviesImageViewHandler = MoviesImageViewHandlerImplementor()
) : RecyclerView.ViewHolder(binding.root),
    MoviesImageViewHandler by moviesImageViewHandler,
    KoinComponent {

    private val aspectRatioHandler by inject<AspectRatioHandler>()

    init {
        moviesImageViewHandler.setMovieImageWidthAndHeight(binding.imageMovie, aspectRatioHandler)
    }

    fun bindData(query: String, data: Content) {
        setMovieTitle(query, data.name)
        moviesImageViewHandler.setImage(binding.imageMovie, data.posterImage)
    }

    private fun setMovieTitle(query: String, title: String) {
        val startIndex = title.indexOf(query, ignoreCase = true)
        val endIndex = startIndex + query.length
        val spannableString = SpannableString(title)
        spannableString.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.textMovieTitle.text = spannableString
    }
}
