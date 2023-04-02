package com.android.domain.usecase

import com.android.domain.error.ErrorHandler
import com.android.domain.model.Content
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
class SearchMoviesUseCaseTest {

    private lateinit var cut: SearchMoviesUseCase

    @RelaxedMockK
    private lateinit var moviesRepository: MoviesRepository

    @RelaxedMockK
    private lateinit var errorHandler: ErrorHandler

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        cut = SearchMoviesUseCase(moviesRepository = moviesRepository, errorHandler = errorHandler)
    }

    @Test
    fun `Given there are results When searchMovies method invoked Then success result returned`() {

        // Given
        val searchResults = mockk<List<Content>>(relaxed = true)
        coEvery { moviesRepository.searchMovies(any()) } returns searchResults

        // When
        runBlockingTest {
            val result = cut.searchMovies("test")

            // Then
            Assert.assertEquals(Result.Success(searchResults), result)
        }
    }

    @Test
    fun `Given io exception error When searchMovies method invoked Then error result returned`() {

        // Given
        val throwable = Throwable("Error occurred")
        coEvery { moviesRepository.searchMovies(any()) } throws Exception(throwable)
        every { errorHandler.getError(any()) } returns ErrorType.FileError

        // When
        runBlockingTest {
            val result = cut.searchMovies("test")

            // Then
            Assert.assertEquals(Result.Error<Movie>(ErrorType.FileError), result)
        }
    }

    @Test
    fun `Given generic exception error When searchMovies method invoked Then error result returned`() {

        // Given
        val throwable = Throwable("Error occurred")
        coEvery { moviesRepository.searchMovies(any()) } throws Exception(throwable)
        every { errorHandler.getError(any()) } returns ErrorType.GenericError(throwable)

        // When
        runBlockingTest {
            val result = cut.searchMovies("test")

            // Then
            Assert.assertEquals(Result.Error<Movie>(ErrorType.GenericError(throwable)), result)
        }
    }
}
