package com.android.domain.usecase

import com.android.domain.error.ErrorHandler
import com.android.domain.model.Movie
import com.android.domain.repository.MoviesRepository
import com.android.domain.response.ErrorType
import com.android.domain.response.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest {

    private lateinit var cut: GetMoviesUseCase

    @RelaxedMockK
    private lateinit var moviesRepository: MoviesRepository

    @RelaxedMockK
    private lateinit var errorHandler: ErrorHandler

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        cut = GetMoviesUseCase(moviesRepository = moviesRepository, errorHandler = errorHandler)
    }

    @Test
    fun `Given there are movies When getMovies method invoked Then success result returned`() {

        // Given
        val movie = mockk<Movie>(relaxed = true)
        coEvery { moviesRepository.getMovies(any()) } returns movie

        // When
        runBlockingTest {
            val result: Result<Movie> = cut.getMovies(1)

            // Then
            Assert.assertEquals(Result.Success(movie), result)
        }
    }

    @Test
    fun `Given io exception error When getMovies method invoked Then error result returned`() {

        // Given
        val throwable = Throwable("Error occurred")
        coEvery { moviesRepository.getMovies(any()) } throws Exception(throwable)
        every { errorHandler.getError(any()) } returns ErrorType.FileError

        // When
        runBlockingTest {
            val result = cut.getMovies(1)

            // Then
            Assert.assertEquals(Result.Error<Movie>(ErrorType.FileError), result)
        }
    }

    @Test
    fun `Given generic exception error When getMovies method invoked Then error result returned`() {

        // Given
        val throwable = Throwable("Error occurred")
        coEvery { moviesRepository.getMovies(any()) } throws Exception(throwable)
        every { errorHandler.getError(any()) } returns ErrorType.GenericError(throwable)

        // When
        runBlockingTest {
            val result = cut.getMovies(1)

            // Then
            Assert.assertEquals(Result.Error<Movie>(ErrorType.GenericError(throwable)), result)
        }
    }
}
