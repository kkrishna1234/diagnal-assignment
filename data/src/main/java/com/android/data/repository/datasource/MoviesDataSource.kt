package com.android.data.repository.datasource

import com.android.data.entity.movies.ContentEntity
import com.android.data.entity.movies.MovieEntity

interface MoviesDataSource {

    suspend fun getMovies(pageNumber: Int): MovieEntity

    suspend fun searchMovies(query: String): List<ContentEntity>
}
