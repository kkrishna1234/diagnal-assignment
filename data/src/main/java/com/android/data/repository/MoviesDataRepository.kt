package com.android.data.repository

import com.android.data.mappers.contentEntityMapper
import com.android.data.mappers.movieEntityMapper
import com.android.data.repository.datasource.MoviesLocalDataSource
import com.android.domain.model.Content
import com.android.domain.model.Movie
import com.android.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesDataRepository(
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesRepository {

    override suspend fun getMovies(pageNumber: Int): Movie {
        return withContext(dispatcher) {
            movieEntityMapper(moviesLocalDataSource.getMovies(pageNumber = pageNumber))
        }
    }

    override suspend fun searchMovies(query: String): List<Content> {
        return withContext(dispatcher) {
            moviesLocalDataSource.searchMovies(query = query).map(contentEntityMapper)
        }
    }
}
