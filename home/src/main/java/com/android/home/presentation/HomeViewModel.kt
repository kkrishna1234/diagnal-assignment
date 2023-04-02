package com.android.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.domain.model.Content
import com.android.home.presentation.datasource.MoviesPagingSource
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val moviesPagingSource: MoviesPagingSource) : ViewModel() {

    private val _movieListFlow = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        moviesPagingSource
    }.flow
        .cachedIn(viewModelScope)
    val movieListFlow: Flow<PagingData<Content>>
        get() = _movieListFlow

    companion object {
        private const val PAGE_SIZE = 20
    }
}
