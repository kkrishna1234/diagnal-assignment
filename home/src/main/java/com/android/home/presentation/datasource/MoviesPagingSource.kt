package com.android.home.presentation.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.core.constants.Constants
import com.android.domain.model.Content
import com.android.domain.response.Result
import com.android.domain.usecase.GetMoviesUseCase

class MoviesPagingSource(private val getMoviesUseCase: GetMoviesUseCase) :
    PagingSource<Int, Content>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            return when (val response = getMoviesUseCase.getMovies(nextPageNumber)) {
                is Result.Error -> LoadResult.Error(
                    response.error.throwable
                        ?: Throwable(Constants.Error.SOMETHING_WENT_WRONG)
                )
                is Result.Success -> LoadResult.Page(
                    data = response.data.page.contentItems.content,
                    prevKey = null,
                    nextKey = response.data.page.pageNumber + 1
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Content>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
