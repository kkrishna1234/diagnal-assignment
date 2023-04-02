package com.android.home.presentation.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.core.state.UiState
import com.android.domain.model.Content
import com.android.domain.response.ErrorType
import com.android.domain.response.Result
import com.android.domain.usecase.SearchMoviesUseCase
import com.android.home.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val context: Application,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : AndroidViewModel(context) {

    private val _uiState = MutableStateFlow<UiState<Pair<String, List<Content>>>>(UiState.None)

    val uiState: StateFlow<UiState<Pair<String, List<Content>>>> = _uiState

    fun search(query: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            when (val result = searchMoviesUseCase.searchMovies(query)) {
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        handleError(ErrorType.ContentUnavailable)
                    } else {
                        _uiState.value = UiState.Success(Pair(query, result.data))
                    }
                }
                is Result.Error -> {
                    handleError(result.error)
                }
            }
        }
    }

    private fun handleError(error: ErrorType) {
        _uiState.value = UiState.Error(
            when (error) {
                ErrorType.ContentUnavailable -> context.resources.getString(R.string.no_results_found)
                ErrorType.FileError -> context.resources.getString(R.string.file_error)
                is ErrorType.GenericError -> context.resources.getString(R.string.generic_error)
            }
        )
    }
}
