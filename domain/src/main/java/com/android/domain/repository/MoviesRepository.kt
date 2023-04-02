package com.android.domain.repository

import com.android.domain.model.Content
import com.android.domain.model.Movie

interface MoviesRepository {

    suspend fun getMovies(pageNumber: Int): Movie

    suspend fun searchMovies(query: String): List<Content>
}
