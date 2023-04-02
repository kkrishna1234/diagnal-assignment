package com.android.domain.usecase

import com.android.domain.error.ErrorHandler
import com.android.domain.model.Movie
import com.android.domain.repository.MoviesRepository
import com.android.domain.response.Result

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository,
    private val errorHandler: ErrorHandler
) {

    suspend fun getMovies(pageNumber: Int): Result<Movie> {
        return try {
            Result.Success(moviesRepository.getMovies(pageNumber = pageNumber))
        } catch (throwable: Throwable) {
            Result.Error(errorHandler.getError(throwable))
        }
    }
}
