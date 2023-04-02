@file:OptIn(ExperimentalCoroutinesApi::class)

package com.android.home.search

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.core.state.UiState
import com.android.domain.model.Content
import com.android.domain.response.ErrorType
import com.android.domain.response.Result
import com.android.domain.usecase.SearchMoviesUseCase
import com.android.home.MainDispatcherRule
import com.android.home.R
import com.android.home.presentation.search.SearchViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class SearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()

    private lateinit var cut: SearchViewModel

    @RelaxedMockK
    private lateinit var context: Application

    @RelaxedMockK
    private lateinit var searchMoviesUseCase: SearchMoviesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        cut = SearchViewModel(context, searchMoviesUseCase)
    }

    @Test
    fun `Given search screen loaded When view model initialised Then ui state nothing emitted`() {

        runTest {
            // When
            cut = SearchViewModel(context, searchMoviesUseCase)

            // Then
            assertEquals(UiState.None, cut.uiState.first())
        }
    }

    @Test
    fun `Given search result fetch in progress When search method invoked Then loading state emitted`() {

        runTest {
            // When
            cut = SearchViewModel(context, searchMoviesUseCase)
            cut.search("test")

            // Then
            assertEquals(UiState.Loading, cut.uiState.first())
        }
    }

    @Test
    fun `Given there are results When search method invoked Then success state emitted`() {

        runTest {

            // Given
            val query = "test"
            val searchResults = mockk<List<Content>>(relaxed = true)
            coEvery { searchMoviesUseCase.searchMovies(query) } returns Result.Success(searchResults)

            // When
            cut.search(query)

            // Then
            assertEquals(UiState.Success(Pair(query, searchResults)), cut.uiState.first())
        }
    }

    @Test
    fun `Given file error while fetching search results When search method invoked Then error state emitted`() {

        runTest {
            // Given
            val query = "test"
            coEvery { searchMoviesUseCase.searchMovies(query) } returns Result.Error(ErrorType.FileError)

            // When
            cut.search(query)

            // Then
            assertEquals(UiState.Error(context.getString(R.string.file_error)), cut.uiState.first())
        }
    }

    @Test
    fun `Given there are no results When search method invoked Then error state emitted`() {
        runTest {
            // Given
            coEvery { searchMoviesUseCase.searchMovies(any()) } returns Result.Success(emptyList())

            // When
            cut.search("test")

            // Then
            assertEquals(
                UiState.Error(context.getString(R.string.no_results_found)),
                cut.uiState.first()
            )
        }
    }

    @Test
    fun `Given generic error When search method invoked Then error state emitted`() {

        runTest {
            // Given
            coEvery { searchMoviesUseCase.searchMovies(any()) } returns Result.Error(
                ErrorType.GenericError(Throwable())
            )

            // When
            cut.search("test")

            // Then
            assertEquals(
                UiState.Error(context.getString(R.string.generic_error)),
                cut.uiState.first()
            )
//            val capturedResult = CapturingSlot<String>()
//            verifyOrder {
//                progressBarObserver.onChanged(false)
//                errorObserver.onChanged(capture(capturedResult))
//            }
//            Assert.assertEquals(context.getString(R.string.generic_error), capturedResult.captured)
        }
    }
}
