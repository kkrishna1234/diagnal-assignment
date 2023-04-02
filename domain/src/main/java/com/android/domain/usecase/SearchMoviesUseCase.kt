package com.android.domain.usecase

import com.android.domain.error.ErrorHandler
import com.android.domain.model.Content
import com.android.domain.repository.MoviesRepository
import com.android.domain.response.Result

class SearchMoviesUseCase(
    private val moviesRepository: MoviesRepository,
    private val errorHandler: ErrorHandler
) {

    suspend fun searchMovies(query: String): Result<List<Content>> {
        return try {
            Result.Success(moviesRepository.searchMovies(query = query))
        } catch (throwable: Throwable) {
            Result.Error(errorHandler.getError(throwable))
        }
    }
}
